# Read Me First
REST API that accepts a list of country codes and returns a list of country codes that are in the same continent as the country code input

# Getting Started

### Build & start the application
* Step 1 - Build Application:  
Run command:		gradlew bootJar  
* Step 2 - Containerize:  
Run command:		docker build --tag=takehome:latest .  
* Step 3 - Run container:  
	Run command:		docker run -p8080:8080 takehome:latest  

### Calling API

API can be called using URI: http://localhost:8080/takehome/continent-countries?countryCode=US&countryCode=US  

Query Parameters:  
countryCode: pass country codes  

Atleast one countrycode is required. Id not passed it will throw error 400 Bad request will be thrown

### Assumption and notes

* Picked SpringBoot version 2.7.5 instead of 3.0.0 because 3.0.0 is not a GA release and will not be good for production
* Didn't added too much code comments but i do practise that in real job


