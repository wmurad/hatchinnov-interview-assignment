package com.example.takehome.data;

import java.time.Duration;

import org.springframework.stereotype.Repository;

import com.example.takehome.data.model.ApiAccessPlan;

@Repository
public class ApiAccessPlanRepository {
	
	public enum ApiAccessPlanType {
		AUTHENTICATED, UNAUTHENTICATED;
	}
	
	public ApiAccessPlan getApiAcessPlan(String code) {
		ApiAccessPlan apiAccessPlan = null;
		
		if (code.equalsIgnoreCase(ApiAccessPlanType.AUTHENTICATED.name())) {
			apiAccessPlan = new ApiAccessPlan();
			apiAccessPlan.setApiAccessPlanId(1);
			apiAccessPlan.setDuration(Duration.ofSeconds(1));
			apiAccessPlan.setLimit(20);
			apiAccessPlan.setPlanCode("AUTHENTICATED");
			apiAccessPlan.setPlanName("Authenticated");
			
		} else if (code.equalsIgnoreCase(ApiAccessPlanType.UNAUTHENTICATED.name())) {
			apiAccessPlan = new ApiAccessPlan();
			apiAccessPlan.setApiAccessPlanId(2);
			apiAccessPlan.setDuration(Duration.ofSeconds(1));
			apiAccessPlan.setLimit(5);
			apiAccessPlan.setPlanCode("UNAUTHENTICATED");
			apiAccessPlan.setPlanName("Unauthenticated");
		} 
		
		return apiAccessPlan;
	}
	
}
