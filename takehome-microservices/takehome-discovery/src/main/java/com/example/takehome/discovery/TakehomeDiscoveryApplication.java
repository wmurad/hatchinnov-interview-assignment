package com.example.takehome.discovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class TakehomeDiscoveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(TakehomeDiscoveryApplication.class, args);
	}

}
