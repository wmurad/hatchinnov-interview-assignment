package com.example.takehome.service.plan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.takehome.data.ApiAccessPlanRepository;
import com.example.takehome.data.model.ApiAccessPlan;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ApiAccessPlanService {
	
	@Autowired
	private ApiAccessPlanRepository apiAccessPlanRepository;
	
	public ApiAccessPlan resolvePlan(boolean isAuthenticated) {
		log.info("get access plan for isAuthenticated: " + isAuthenticated);
		if (isAuthenticated) {
			return apiAccessPlanRepository.getApiAcessPlan(ApiAccessPlanRepository.ApiAccessPlanType.AUTHENTICATED.toString());
		} else {
			return apiAccessPlanRepository.getApiAcessPlan(ApiAccessPlanRepository.ApiAccessPlanType.UNAUTHENTICATED.toString());
		}
	}
	
}
