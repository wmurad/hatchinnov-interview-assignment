package com.example.takehome.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.takehome.data.ContinentRepository;
import com.example.takehome.data.CountryRepository;
import com.example.takehome.dto.ContinentCountriesDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ContinentCountriesService {

	@Autowired
	private CountryRepository countryDao;
	
	@Autowired
	private ContinentRepository continentDao;
	
	public List<ContinentCountriesDto> getContinentCountries(List<String> countryCodes) {
		log.info("Service ContinentCountries");
		
		ContinentCountriesDto continentCountriesDto;
		HashMap<String,ContinentCountriesDto> resultsMap = new HashMap<String,ContinentCountriesDto>();
		
		// iterate through each country in a list
		for (String countryCode: countryCodes) {
			log.info("Generating result for " + countryCode);
			// get continent code
			String continentCode = countryDao.getCountryContinentCode(countryCode);
			
			// if code is not found it means invalid country code was passed so ignore and continue
			if (continentCode == null) {
				log.debug(continentCode + "not found");
				continue;
			}
			
			// get from map continent information that might have been prepared
			continentCountriesDto = resultsMap.get(continentCode);
			
			// if not present then create the result and add to map
			if (continentCountriesDto == null) {
				continentCountriesDto = new ContinentCountriesDto();
				continentCountriesDto.setOtherCountries(continentDao.getContinentCountriesAsSet(continentCode));
				continentCountriesDto.getOtherCountries().remove(countryCode);
				continentCountriesDto.setCountries(new HashSet<String>());
				continentCountriesDto.getCountries().add(countryCode);
				continentCountriesDto.setName(continentDao.getContinentName(continentCode));
				resultsMap.put(continentCode, continentCountriesDto);
			// otherwise update the same map by moving the country code	
			} else {
				log.debug("Updating existingcontinent result");
				continentCountriesDto.getCountries().add(countryCode);
				continentCountriesDto.getOtherCountries().remove(countryCode);
			}
		}
		
		// convert to all values to list and return
		List<ContinentCountriesDto> result = new ArrayList<ContinentCountriesDto>(resultsMap.values());
		return result;
	}
	
}
