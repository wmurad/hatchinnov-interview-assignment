# Read Me First
REST API that accepts a list of country codes and returns a list of country codes that are in the same continent as the country code input parameters.

## Design
API is build on Microservices Architecture. Once started it spins up below components/containers.

* **discovery-server**: Registry & discovery service/server containing inforamtion about clients. ** Deliberate its left accesable at http://localhost:8761/ just incase if you want to have a look**
* **configuration-server**: Configuration server for externalizing applicaiton configurations.
* **gateway-server**: API Gateway for all ingress traffic and routing it to backend servcies.
* **oauth-server**: OAUTH services
* **takehome-api**: API that return continent and its country information.
* **redis-server**: Distrubuted cashe used for api rate limiting functionality.
* **takehome-services-network**: network in which above container resides.

**Note: [ Future Enhancements ]**
Due to submittion as of today i was not able to incorporate below enhacements/changes but had considered them in my design.
1. Build backend database container and fetch OAuth, Country & API Rate Limit plans from database.
2. SSL on API gateway.
3. Not all services/apis configurations are externalize yet to configuration server. Plus also properties location for configuration server are not yet to some git location or extrnal file system.
4. OAuth server is ready but api is not authenticated yet using OAuth. As of now its api-key based authentication.
5. Rate Limiting is implemented in api but needs to be moved at api gateway by using spring cloud gateway RequestRateLimiter filter and redis-rate-limiter argument. Providing KeyResolver interface implementation to fetch key based on user authenticaton.
6. log files should be places to some external volume attached 

# Getting Started

### Build & start application
Go to folder **takehome-microservices** and run below command. Make sure JAVA_HOME is set to jdk 17 and docker is running. 

* docker compose up --build

**Note:** Run command prompt as administrator. Please give sometime so services are discovered once started.

### Consuming API

Sample postman collection has been provided with submission i.e. takehome-api.postman_collection.json See below requests inside collection that can be used to call api.

* takehome-oauth-micro: Can be called to get oauth token. As mentioned above oauth is not integrated with api.
* takehome-continentcountry-micro: Call it to fetch country and continent information. Once application is started API can be called using URI http://localhost:8080/takehome/continent-countries?countryCode=US&countryCode=US  

Query Parameters:  
countryCode: country codes to get the information for

Atleast one countrycode is required. If not passed it will throw error 400 Bad request will be thrown

As of now api is authenticated using **api-key** header. if header is not present its considered as unauthenticated request. if present then api key is validated. Valid key values as of now are:

* 61b01cdb-6182-435d-a05a-51c52d5822e8
* c899e310-0ed6-4696-923b-f090f581e3d6
* be9ba05b-91ab-4b01-97de-91b12b7b2829

### Assumption and Notes

* Picked SpringBoot version 2.7.5 instead of 3.0.0 because 3.0.0 is not a GA release and will not be good for production
* Didn't added too much code comments but i do practise that.


