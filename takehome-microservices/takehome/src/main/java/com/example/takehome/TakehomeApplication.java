package com.example.takehome;

import org.redisson.spring.starter.RedissonAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
@EnableEurekaClient
@EnableAutoConfiguration(exclude = { RedissonAutoConfiguration.class })
public class TakehomeApplication {
	
	public static void main(String[] args) {
		log.debug("TakehomeApplication Starting");
		SpringApplication.run(TakehomeApplication.class, args);
	}

}
