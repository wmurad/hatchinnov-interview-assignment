package com.example.takehome.controller.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.example.takehome.exception.ApiError;
import com.example.takehome.service.auth.AuthenticationService;
import com.example.takehome.util.TakeHomeConstants;
import com.example.takehome.util.TakeHomeUtility;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

	@Autowired 
	AuthenticationService authenticationService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String apiKey = request.getHeader(TakeHomeConstants.API_KEY_HEADER_NAME);
		
		if (apiKey != null && !authenticationService.authenticateApi(apiKey)) {
			ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED, "Unauthorized");
			ObjectMapper objectMapper = TakeHomeUtility.getObjectmapper();
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			response.getWriter().write(objectMapper.writeValueAsString(apiError));
			return false;
		}
		
		return true;
	}
}
