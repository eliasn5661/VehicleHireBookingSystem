# Vehicle Hire Booking System
The source code supplied is to provide technical solution for Babcock Technical Test - Vehicle Hire Booking System.

## Requirements

A vehicle hire company has a number of cars and vans, and a variety of customers, some of whom are individuals who might only hire one vehicle at a time and some of whom are companies 
who might have multiple vehicles on hire at any one time. The hire company has many vehicles of the same make and model. Vehicles are hired on a daily basis, rather than hourly.
They have asked for a simple system to be written to keep a record of the vehicles they own and which ones are currently hired out to customers.

## Technologies used:
Spring boot, JPA/Hibernate , Restful , H2 in memory database, swagger for documentation.

I haven't used Vue.js as I have never used it only if I had more time.
I know Vaadin framework, however is very tricky to use it with Restful apis and would take more time to setup.
Instead I used Postman to validate and test the apis provided

## Running the code:

**Maven** is used for this project
The following command is to generate VehicleHireBookingSystem-0.0.1-SNAPSHOT.jar in the target directory
**mvn package**

To run the application
change directory to target then execute the following:

**java -jar VehicleHireBookingSystem-0.0.1-SNAPSHOT.jar**
	

## Swagger Documents
the url is : 
http://localhost:8080/swagger-ui.html

## Database

H2 in memory database is used to hold information
access the data using
http://localhost:8080/h2-console
username:sa
password:password

**schema.sql** and **data.sql** both files found under resources to initialize some data for Vehicles and Customers

## JUNIT Tests
I could have added more to achieve 100% of code coverage, due to time constraint this was not possible.

## Manual Testing

**Postman** is used for testing

### 1) List available vehicles for hire – all vehicles that can be hired today

1. **url:** localhost:8080/api/
**result:**
[
    {
        "registrationNumber": "N20FAL",
        "make": "Mercedes",
        "model": "E230",
        "fuelType": "Diesel",
        "vehicleTypeId": 1,
        "vehicleType": "Small",
        "pricePerDay": 25.0
    },
    {
        "registrationNumber": "CK20PPO",
        "make": "KIA",
        "model": "eNiro",
        "fuelType": "Petrol",
        "vehicleTypeId": 2,
        "vehicleType": "Estate",
        "pricePerDay": 35.0
    },
    {
        "registrationNumber": "CK19CVS",
        "make": "VW",
        "model": "ESPRO",
        "fuelType": "Diesel",
        "vehicleTypeId": 3,
        "vehicleType": "Van",
        "pricePerDay": 50.0
    },
    {
        "registrationNumber": "CK18CLD",
        "make": "BMW",
        "model": "380i",
        "fuelType": "Diesel",
        "vehicleTypeId": 1,
        "vehicleType": "Small",
        "pricePerDay": 25.0
    },
    {
        "registrationNumber": "SW17JKD",
        "make": "AUDI",
        "model": "Quatro 360",
        "fuelType": "Petrol",
        "vehicleTypeId": 2,
        "vehicleType": "Estate",
        "pricePerDay": 35.0
    },
    {
        "registrationNumber": "CK19JWD",
        "make": "Mercedes",
        "model": "XMAN",
        "fuelType": "Diesel",
        "vehicleTypeId": 3,
        "vehicleType": "Van",
        "pricePerDay": 50.0
    }
]

### 2. Book a vehicle
**URL:**localhost:8080/api/VehicleHireBooking/book?customerId=1&vehcileRegNumber=N20FAL&hireDateStr=29/11/2020&returnDateStr=30/12/2020

now issue to list available vehicles as in **test 1**, you will see vehicle N20FAL is no longer in the list

### 3.	Calculate cost of hiring a specific vehicle for a provided date range

**URL:** localhost:8080/api/VehicleHireBooking/query?vehicleRegNumber=CK19JWD&hireDateStr=01/12/2020&returnDateStr=01/12/2020

**result** :
Cost to hire Mercedes model XMAN car type Van is 
50.0
**URL:** localhost:8080/api/VehicleHireBooking/query?vehicleRegNumber=N20FAL&hireDateStr=01/12/2020&returnDateStr=04/12/2020

**result** :
Cost to hire Mercedes model E230 car type Small is 
75.0

**URL:** localhost:8080/api/VehicleHireBooking/query?vehicleRegNumber=CK20PPO&hireDateStr=01/12/2020&returnDateStr=04/12/2020
Cost to hire KIA model eNiro car type Estate is 
105.0

**URL:** localhost:8080/api/VehicleHireBooking/query?vehicleRegNumber=xxxx&hireDateStr=01/12/2020&returnDateStr=04/12/2020
Car not found 

**URL:** localhost:8080/api/VehicleHireBooking/query?vehicleRegNumber=CK20PPO&hireDateStr=01/12/2020&returnDateStr=04/11/2020
Hire date is after return date

**URL:** localhost:8080/api/VehicleHireBooking/query?vehicleRegNumber=CK20PPO&hireDateStr=01/11/2020&returnDateStr=04/12/2020
Hire date must not be in the past

