package com.example.takehome.controller.security;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.example.takehome.data.model.ApiAccessPlan;
import com.example.takehome.exception.ApiError;
import com.example.takehome.service.plan.ApiAccessPlanService;
import com.example.takehome.util.TakeHomeConstants;
import com.example.takehome.util.TakeHomeUtility;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Scope("singleton")
public class RateLimiterInterceptor implements HandlerInterceptor {

	private static final Map<String, Bucket> cache = new ConcurrentHashMap<String, Bucket>();
	
	@Autowired
	ApiAccessPlanService apiAccessPlanService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String bucketKey = request.getRemoteAddr();
		String apiKeyHeader = request.getHeader(TakeHomeConstants.API_KEY_HEADER_NAME);
		
		boolean isAuthenticated = false;
		
		if (apiKeyHeader != null) {
			isAuthenticated = true;
			bucketKey = apiKeyHeader;
		}
		
		Bucket bucket = resolveBucket(isAuthenticated, bucketKey);
		
        boolean isConsumed = bucket.tryConsume(1);
        log.info("Rate Limit - Remote Address:" + request.getRemoteAddr() +" | isAuthenticated: " + isAuthenticated + " | consumed: " + isConsumed);
        
        if (!isConsumed) {
        	ApiError apiError = new ApiError(HttpStatus.TOO_MANY_REQUESTS, "You have exhausted your API Request Quota");
			ObjectMapper objectMapper = TakeHomeUtility.getObjectmapper();
    		response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
    		response.getWriter().write(objectMapper.writeValueAsString(apiError));
    		return false;
        }
        return true;
	}

	public Bucket resolveBucket(boolean isAuthenticated, String key) {
		Bucket bucket = cache.get(key);
		if (bucket == null) {
			bucket = newBucket(isAuthenticated);
			cache.put(key, bucket);
		}
		return bucket;
	}

	private Bucket newBucket(boolean isAuthenticated) {
		ApiAccessPlan apiAccessPlan = apiAccessPlanService.resolvePlan(isAuthenticated);
		Bandwidth bandwidth = Bandwidth.simple(apiAccessPlan.getLimit(), apiAccessPlan.getDuration());
		return Bucket.builder().addLimit(bandwidth).build();
	}

}
