package com.example.takehome.oauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class TakehomeOauthApplication {

	public static void main(String[] args) {
		SpringApplication.run(TakehomeOauthApplication.class, args);
	}

}
