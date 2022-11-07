package com.example.takehome.data.model;

import java.time.Duration;

import lombok.Data;

@Data
public class ApiAccessPlan {
	
	private Integer apiAccessPlanId;
	private String planName;
	private String planCode;
	private int	limit;
	private Duration duration;
	
}
