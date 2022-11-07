package com.example.takehome.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class TakehomeGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(TakehomeGatewayApplication.class, args);
	}

}
