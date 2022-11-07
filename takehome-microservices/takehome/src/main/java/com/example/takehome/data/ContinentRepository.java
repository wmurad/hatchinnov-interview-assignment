package com.example.takehome.data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import com.example.takehome.util.TakeHomeUtility;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ContinentRepository {
	
	@Value("${takehome.continentDataFilePath}")
	private String continentDataFilePath;
	
	//private static final String CONTINENT_DATA = "{ \"continents\": [ { \"name\": \"Africa\", \"code\": \"AF\", \"countries\": [ { \"code\": \"AO\" }, { \"code\": \"BF\" }, { \"code\": \"BI\" }, { \"code\": \"BJ\" }, { \"code\": \"BW\" }, { \"code\": \"CD\" }, { \"code\": \"CF\" }, { \"code\": \"CG\" }, { \"code\": \"CI\" }, { \"code\": \"CM\" }, { \"code\": \"CV\" }, { \"code\": \"DJ\" }, { \"code\": \"DZ\" }, { \"code\": \"EG\" }, { \"code\": \"EH\" }, { \"code\": \"ER\" }, { \"code\": \"ET\" }, { \"code\": \"GA\" }, { \"code\": \"GH\" }, { \"code\": \"GM\" }, { \"code\": \"GN\" }, { \"code\": \"GQ\" }, { \"code\": \"GW\" }, { \"code\": \"KE\" }, { \"code\": \"KM\" }, { \"code\": \"LR\" }, { \"code\": \"LS\" }, { \"code\": \"LY\" }, { \"code\": \"MA\" }, { \"code\": \"MG\" }, { \"code\": \"ML\" }, { \"code\": \"MR\" }, { \"code\": \"MU\" }, { \"code\": \"MW\" }, { \"code\": \"MZ\" }, { \"code\": \"NA\" }, { \"code\": \"NE\" }, { \"code\": \"NG\" }, { \"code\": \"RE\" }, { \"code\": \"RW\" }, { \"code\": \"SC\" }, { \"code\": \"SD\" }, { \"code\": \"SH\" }, { \"code\": \"SL\" }, { \"code\": \"SN\" }, { \"code\": \"SO\" }, { \"code\": \"SS\" }, { \"code\": \"ST\" }, { \"code\": \"SZ\" }, { \"code\": \"TD\" }, { \"code\": \"TG\" }, { \"code\": \"TN\" }, { \"code\": \"TZ\" }, { \"code\": \"UG\" }, { \"code\": \"YT\" }, { \"code\": \"ZA\" }, { \"code\": \"ZM\" }, { \"code\": \"ZW\" } ] }, { \"name\": \"Antarctica\", \"code\": \"AN\", \"countries\": [ { \"code\": \"AQ\" }, { \"code\": \"BV\" }, { \"code\": \"GS\" }, { \"code\": \"HM\" }, { \"code\": \"TF\" } ] }, { \"name\": \"Asia\", \"code\": \"AS\", \"countries\": [ { \"code\": \"AE\" }, { \"code\": \"AF\" }, { \"code\": \"AM\" }, { \"code\": \"AZ\" }, { \"code\": \"BD\" }, { \"code\": \"BH\" }, { \"code\": \"BN\" }, { \"code\": \"BT\" }, { \"code\": \"CC\" }, { \"code\": \"CN\" }, { \"code\": \"CX\" }, { \"code\": \"GE\" }, { \"code\": \"HK\" }, { \"code\": \"ID\" }, { \"code\": \"IL\" }, { \"code\": \"IN\" }, { \"code\": \"IO\" }, { \"code\": \"IQ\" }, { \"code\": \"IR\" }, { \"code\": \"JO\" }, { \"code\": \"JP\" }, { \"code\": \"KG\" }, { \"code\": \"KH\" }, { \"code\": \"KP\" }, { \"code\": \"KR\" }, { \"code\": \"KW\" }, { \"code\": \"KZ\" }, { \"code\": \"LA\" }, { \"code\": \"LB\" }, { \"code\": \"LK\" }, { \"code\": \"MM\" }, { \"code\": \"MN\" }, { \"code\": \"MO\" }, { \"code\": \"MV\" }, { \"code\": \"MY\" }, { \"code\": \"NP\" }, { \"code\": \"OM\" }, { \"code\": \"PH\" }, { \"code\": \"PK\" }, { \"code\": \"PS\" }, { \"code\": \"QA\" }, { \"code\": \"SA\" }, { \"code\": \"SG\" }, { \"code\": \"SY\" }, { \"code\": \"TH\" }, { \"code\": \"TJ\" }, { \"code\": \"TM\" }, { \"code\": \"TR\" }, { \"code\": \"TW\" }, { \"code\": \"UZ\" }, { \"code\": \"VN\" }, { \"code\": \"YE\" } ] }, { \"name\": \"Europe\", \"code\": \"EU\", \"countries\": [ { \"code\": \"AD\" }, { \"code\": \"AL\" }, { \"code\": \"AT\" }, { \"code\": \"AX\" }, { \"code\": \"BA\" }, { \"code\": \"BE\" }, { \"code\": \"BG\" }, { \"code\": \"BY\" }, { \"code\": \"CH\" }, { \"code\": \"CY\" }, { \"code\": \"CZ\" }, { \"code\": \"DE\" }, { \"code\": \"DK\" }, { \"code\": \"EE\" }, { \"code\": \"ES\" }, { \"code\": \"FI\" }, { \"code\": \"FO\" }, { \"code\": \"FR\" }, { \"code\": \"GB\" }, { \"code\": \"GG\" }, { \"code\": \"GI\" }, { \"code\": \"GR\" }, { \"code\": \"HR\" }, { \"code\": \"HU\" }, { \"code\": \"IE\" }, { \"code\": \"IM\" }, { \"code\": \"IS\" }, { \"code\": \"IT\" }, { \"code\": \"JE\" }, { \"code\": \"LI\" }, { \"code\": \"LT\" }, { \"code\": \"LU\" }, { \"code\": \"LV\" }, { \"code\": \"MC\" }, { \"code\": \"MD\" }, { \"code\": \"ME\" }, { \"code\": \"MK\" }, { \"code\": \"MT\" }, { \"code\": \"NL\" }, { \"code\": \"NO\" }, { \"code\": \"PL\" }, { \"code\": \"PT\" }, { \"code\": \"RO\" }, { \"code\": \"RS\" }, { \"code\": \"RU\" }, { \"code\": \"SE\" }, { \"code\": \"SI\" }, { \"code\": \"SJ\" }, { \"code\": \"SK\" }, { \"code\": \"SM\" }, { \"code\": \"UA\" }, { \"code\": \"VA\" }, { \"code\": \"XK\" } ] }, { \"name\": \"North America\", \"code\": \"NA\", \"countries\": [ { \"code\": \"AG\" }, { \"code\": \"AI\" }, { \"code\": \"AW\" }, { \"code\": \"BB\" }, { \"code\": \"BL\" }, { \"code\": \"BM\" }, { \"code\": \"BQ\" }, { \"code\": \"BS\" }, { \"code\": \"BZ\" }, { \"code\": \"CA\" }, { \"code\": \"CR\" }, { \"code\": \"CU\" }, { \"code\": \"CW\" }, { \"code\": \"DM\" }, { \"code\": \"DO\" }, { \"code\": \"GD\" }, { \"code\": \"GL\" }, { \"code\": \"GP\" }, { \"code\": \"GT\" }, { \"code\": \"HN\" }, { \"code\": \"HT\" }, { \"code\": \"JM\" }, { \"code\": \"KN\" }, { \"code\": \"KY\" }, { \"code\": \"LC\" }, { \"code\": \"MF\" }, { \"code\": \"MQ\" }, { \"code\": \"MS\" }, { \"code\": \"MX\" }, { \"code\": \"NI\" }, { \"code\": \"PA\" }, { \"code\": \"PM\" }, { \"code\": \"PR\" }, { \"code\": \"SV\" }, { \"code\": \"SX\" }, { \"code\": \"TC\" }, { \"code\": \"TT\" }, { \"code\": \"US\" }, { \"code\": \"VC\" }, { \"code\": \"VG\" }, { \"code\": \"VI\" } ] }, { \"name\": \"Oceania\", \"code\": \"OC\", \"countries\": [ { \"code\": \"AS\" }, { \"code\": \"AU\" }, { \"code\": \"CK\" }, { \"code\": \"FJ\" }, { \"code\": \"FM\" }, { \"code\": \"GU\" }, { \"code\": \"KI\" }, { \"code\": \"MH\" }, { \"code\": \"MP\" }, { \"code\": \"NC\" }, { \"code\": \"NF\" }, { \"code\": \"NR\" }, { \"code\": \"NU\" }, { \"code\": \"NZ\" }, { \"code\": \"PF\" }, { \"code\": \"PG\" }, { \"code\": \"PN\" }, { \"code\": \"PW\" }, { \"code\": \"SB\" }, { \"code\": \"TK\" }, { \"code\": \"TL\" }, { \"code\": \"TO\" }, { \"code\": \"TV\" }, { \"code\": \"UM\" }, { \"code\": \"VU\" }, { \"code\": \"WF\" }, { \"code\": \"WS\" } ] }, { \"name\": \"South America\", \"code\": \"SA\", \"countries\": [ { \"code\": \"AR\" }, { \"code\": \"BO\" }, { \"code\": \"BR\" }, { \"code\": \"CL\" }, { \"code\": \"CO\" }, { \"code\": \"EC\" }, { \"code\": \"FK\" }, { \"code\": \"GF\" }, { \"code\": \"GY\" }, { \"code\": \"PE\" }, { \"code\": \"PY\" }, { \"code\": \"SR\" }, { \"code\": \"UY\" }, { \"code\": \"VE\" } ] } ] }";
	private static final HashMap<String, List<String>> continents = new HashMap<String,  List<String>>();
	private static final HashMap<String, String> continentsName = new HashMap<String, String>();
	
	@Autowired
	private ResourceLoader resourceLoader;
	
	@PostConstruct
	public void loadData() throws IOException {
		log.info("Load continent data");
		ObjectMapper objectMapper = TakeHomeUtility.getObjectmapper();
		Resource resource = resourceLoader.getResource(continentDataFilePath);
		String continentData = TakeHomeUtility.readFileAsString(resource.getInputStream());
		//JsonNode jsonNode = objectMapper.readTree(CONTINENT_DATA).get("continents");
		JsonNode jsonNode = objectMapper.readTree(continentData).get("continents");
		for (JsonNode objNode : jsonNode) {
			JsonNode countries = objNode.get("countries");
			List<String> countryList = new ArrayList<String>();
			for (JsonNode countryCode : countries) {
				countryList.add(countryCode.get("code").asText());
			}
			
			continents.put(objNode.get("code").asText(), countryList);
			continentsName.put(objNode.get("code").asText(), objNode.get("name").asText());
	    }
	}
	
	public HashSet<String> getContinentCountriesAsSet(String continentCode) {
		HashSet<String> set = new HashSet<String>(continents.get(continentCode));
		return set;
	}
	
	public String getContinentName(String continentCode) {
		return continentsName.get(continentCode);
	}
	
}
