package com.example.takehome.service.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.takehome.data.ApiClientRepository;

@Service
public class AuthenticationService {
	
	@Autowired
	ApiClientRepository apiClientRepository;
	
	public boolean authenticateApi(String apiKey) {
		return apiClientRepository.authenticateApiClient(apiKey);
	}
	
}
