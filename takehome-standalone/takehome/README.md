# Read Me First
REST API that accepts a list of country codes and returns a list of country codes that are in the same continent as the country code input

# Getting Started

### Build & start the application
* Step 1 - Containerize:  
Run command:		docker build --tag=takehome:latest .  
* Step 2 - Run container:  
	Run command:		docker run -p8080:8080 takehome:latest  

**Note:** Run command prompt as administrator.

### Consuming API

Sample postman collection has been provided with submission i.e. takehome-api.postman_collection.json See below requests inside collection that can be used to call api.

* takehome-continentcountry-standlone: Call it to fetch country and continent information. Once application is started API can be called using URI http://localhost:8080/takehome/continent-countries?countryCode=US&countryCode=US  

Query Parameters:  
countryCode: country codes to get the information for

Atleast one countrycode is required. If not passed it will throw error 400 Bad request will be thrown

As of now api is authenticated using **api-key** header. if header is not present its considered as unauthenticated request. if present then api key is validated. Valid key values as of now are:

* 61b01cdb-6182-435d-a05a-51c52d5822e8
* c899e310-0ed6-4696-923b-f090f581e3d6
* be9ba05b-91ab-4b01-97de-91b12b7b2829

### Assumption and notes

* Picked SpringBoot version 2.7.5 instead of 3.0.0 because 3.0.0 is not a GA release and will not be good for production
* Didn't added too much code comments but i do practise that in real job


