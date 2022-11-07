package com.example.takehome.data;

import org.springframework.stereotype.Repository;

@Repository
public class ApiClientRepository {

	public boolean authenticateApiClient(String apiKey) {
		return apiKey.matches("61b01cdb-6182-435d-a05a-51c52d5822e8|c899e310-0ed6-4696-923b-f090f581e3d6|be9ba05b-91ab-4b01-97de-91b12b7b2829");
	}
}
