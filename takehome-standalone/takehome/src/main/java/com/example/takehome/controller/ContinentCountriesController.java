package com.example.takehome.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.takehome.dto.ContinentCountriesDto;
import com.example.takehome.dto.ContinentCountriesResponseDto;
import com.example.takehome.service.ContinentCountriesService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class ContinentCountriesController {
	
	@Autowired
	private ContinentCountriesService continentCountriesService;
	
	@GetMapping("/continent-countries")
	public ContinentCountriesResponseDto getContinentCountries(@RequestParam(name = "countryCode") List<String> countryCodes) {
		log.info("GET ContinentCountries");
		
		List<ContinentCountriesDto> continentCountries = continentCountriesService.getContinentCountries(countryCodes);
		ContinentCountriesResponseDto continentCountriesResponseDto = new ContinentCountriesResponseDto();
		continentCountriesResponseDto.setContinent(continentCountries);

		log.info("GET ContinentCountries Response Completed");
		return continentCountriesResponseDto;
	}
	
}
