package com.example.takehome;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class TakehomeApplication {
	
	public static void main(String[] args) {
		log.debug("TakehomeApplication Starting");
		SpringApplication.run(TakehomeApplication.class, args);
	}

}
