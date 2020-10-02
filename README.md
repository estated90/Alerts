#SafetyNetAlerts
The main purpose of safetyNetAlerts is to alert people or get information to send to the emergency services.
The application should provide information about people exposed to potential dangers such as fires, floods, hurricanes and so on. To achieve this task, SafetyNetAlerts will order data from a file and order it to extract specific information about people who are living in an area exposed to danger.
Getting Started
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.
#Prerequisites What things you need to install and how will you install them:
1.	Java 1.8 or later.
2.	Maven 3.6.3 (It is optional since the maven wrapper is present in the project)
3.	Postman 7 is optional. The application can be used as well with a browser.
 #Installing A step by step series of examples that tell you how to get a development environment:
1.Install Java:
https://docs.oracle.com/javase/8/docs/technotes/guides/install/install_overview.html
2.Install Maven:
https://maven.apache.org/install.html
3.Install Postman:
https://learning.postman.com/docs/getting-started/installation-and-updates/
#Running App
After installing all the required software, you will be ready to import the code into your favourite IDE and run the SafetyNetAlertsApplication.java to launch the application.
Firestation
GET
•	To retrieve all fire stations
Syntax
•	http://localhost:8080/fireStation
Parameters
•	None
Exemple
[    
    {
        "address": "1509 Culver St",
        "station": 3
    }
]
Notes
API will return the Json and a 200 OK code if success
POST
•	You can add a new fire station
Syntax
•	http://localhost:8080/fireStation 
Parameters
•	Address is mandatory 
•	Station is mandatory 
Exemple
{
    "address": "1509 Culver St",
    "station": "3"
}
Notes
API will return a 204 No Content code if success
API will return a 400 Bad Request code if you didn’t provide all the element
PUT
•	Update an existing fire station 
Syntax
•	http://localhost:8080/fireStation
Parameters
•	Address is mandatory to update the station id
•	Station is mandatory to be updated
Exemple
{
    "address": "1509 Culver St",
    "station": "3"
}
Notes
API will return a 201 Created code if success
API will return a 422 Unprocessable Entity with message “Address was unknown”, if the fire station is not found
API will return a 400 Bad Request code if you didn’t provide all the element
DELETE
•	Delete an existing fire station 
Syntax
•	http://localhost:8080/fireStation
Parameters
•	Address OR station are mandatory to delete the station
Exemple
{
    "address": "1509 Culver St",
    "station": "3"
}
Notes
API will return a 200 Ok code if success
API will return a 422 Unprocessable Entity with message “Address was unknown”, if the fire station is not found
API will return a 400 Bad Request code if you didn’t provide all the element
Person
GET
•	To retrieve all person
Syntax
•	http://localhost:8080/person 
Parameters
•	None
Exemple
[
    {
        "firstName": "John",
        "lastName": "Boyd",
        "address": "1509 Culver St",
        "city": "Culver",
        "zip": "97451",
        "phone": "841-874-6512",
        "email": "jaboyd@email.com"
    }
]
Notes
API will return the Json and a 200 OK code if success
POST
•	You can add a new person
Syntax
•	http://localhost:8080/person  
Parameters
•	All fields are mandatory
Exemple
    {
        "firstName": "John",
        "lastName": "Boyd",
        "address": "1509 Culver St",
        "city": "Culver",
        "zip": "97451",
        "phone": "841-874-6512",
        "email": "jaboyd@email.com"
    }
Notes
API will return a 204 No Content code if success
API will return a 400 Bad Request code if you didn’t provide all the element
PUT
•	Update an existing person 
Syntax
•	http://localhost:8080/person 
Parameters
•	First Name and Last Name are mandatory to find the person to modify
•	All other fields are also mandatory
Exemple
[
    {
        "firstName": "John",
        "lastName": "Boyd",
        "address": "1509 Culver St",
        "city": "Culver",
        "zip": "97451",
        "phone": "841-874-6512",
        "email": "jaboyd@email.com"
    }
]
 Notes
API will return a 201 Created code if success
API will return a 422 Unprocessable Entity if the person is not found
API will return a 400 Bad Request code if you didn’t provide all the element
DELETE
•	Delete an existing person
Syntax
•	http://localhost:8080/person 
Parameters
•	firstName and lastName are mandatory to process request
Exemple
[
    {
        "firstName": "John",
        "lastName": "Boyd",
    }
]
Notes
API will return a 200 Ok code if success
API will return a 422 Unprocessable Entity if the person is not found
API will return a 400 Bad Request code if you didn’t provide all the element
Medicalrecord
GET
•	To retrieve all medical record
Syntax
•	http://localhost:8080/medicalrecord 
Parameters
•	None
Exemple
[
    {
        "firstName": "John",
        "lastName": "Boyd",
        "birthdate": "1984-06-03",
        "medications": [
            "aznol:350mg",
            "hydrapermazol:100mg"
        ],
        "allergies": [
            "nillacilan"
        ]
    }
]
Notes
API will return the Json and a 200 OK code if success
POST
•	You can add a new medical record
Syntax
•	 http://localhost:8080/medicalrecord  
Parameters
•	All fields are mandatory
Exemple
    {
        "firstName": "John",
        "lastName": "Boyd",
        "birthdate": "1984-06-03",
        "medications": [
            "aznol:350mg",
            "hydrapermazol:100mg"
        ],
        "allergies": [
            "nillacilan"
        ]
    }
Notes
API will return a 204 No Content code if success
API will return a 400 Bad Request code if you didn’t provide all the element
PUT
•	Update an existing person 
Syntax
•	 http://localhost:8080/medicalrecord  
Parameters
•	First Name and Last Name are mandatory to find the person to modify
•	All other fields are also mandatory
Exemple
[
    {
        "firstName": "John",
        "lastName": "Boyd",
        "birthdate": "1984-06-03",
        "medications": [
            "aznol:350mg",
            "hydrapermazol:100mg"
        ],
        "allergies": [
            "nillacilan"
        ]
    }
]
 Notes
API will return a 201 Created code if success
API will return a 422 Unprocessable Entity if the medical record is not found
API will return a 400 Bad Request code if you didn’t provide all the element
DELETE
•	Delete an existing medical record
Syntax
•	http://localhost:8080/medicalrecord 
Parameters
•	firstName and lastName are mandatory to process request
Exemple
[
    {
        "firstName": "John",
        "lastName": "Boyd",
    }
]
Notes
API will return a 200 Ok code if success
API will return a 422 Unprocessable Entity if the medical record is not found
API will return a 400 Bad Request code if you didn’t provide all the element
URLS
GET
•	To retrieve a list of persons covered by a fire station: 
Syntaxe
•	http://localhost:8080/fireStation/stationNumber?id=<fire_station_id>
Parameters
•	The number of the station number
 Exemple
{
    "person": [
        {
            "firstName": "Peter",
            "lastName": "Duncan",
            "address": "644 Gershwin Cir",
            "phone": "841-874-6512"
        }
    ],
    "numberOfAdult": 5,
    "numberOfChildren": 1
}
Notes
The URL will return a list of persons that are covered by the station ID provided. The list will return first name, last name, address and phone. Moreover, it will include the number of adult and child (under 18) from the covered area.
#Testing
Use either mvn test if you have maven on your machine or ./mvnw test if you do not have maven on your machine.
To get all the different reports, you can use either mvn site or ./mvnw site
You can find the generated test reports in the target directory.
