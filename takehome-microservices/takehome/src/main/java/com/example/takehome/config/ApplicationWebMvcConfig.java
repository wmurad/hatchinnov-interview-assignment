package com.example.takehome.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.takehome.controller.security.AuthenticationInterceptor;
import com.example.takehome.controller.security.RateLimiterInterceptor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class ApplicationWebMvcConfig implements WebMvcConfigurer {
	
	@Autowired
	private RateLimiterInterceptor rateLimiterInterceptor;
	
	@Autowired
	private AuthenticationInterceptor authenticationInterceptor;
	
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
    	log.info("Adding interceptors");
        registry.addInterceptor(authenticationInterceptor).order(1);
        registry.addInterceptor(rateLimiterInterceptor).order(2);
    }
    
}