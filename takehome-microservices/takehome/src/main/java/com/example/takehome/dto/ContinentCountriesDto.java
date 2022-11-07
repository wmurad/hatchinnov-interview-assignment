package com.example.takehome.dto;

import java.util.HashSet;

import lombok.Data;

@Data
public class ContinentCountriesDto {

	private HashSet<String> countries;
	private String name;
	private HashSet<String> otherCountries;
	
}
