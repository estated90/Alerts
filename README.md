# SafetyNetAlerts

The main purpose of safetyNetAlerts is to alert people or get information to send to the emergency services.

The application should provide information about people exposed to potential dangers such as fires, floods, hurricanes and so on. To achieve this task, SafetyNetAlerts will order data from a file and order it to extract specific information about people who are living in an area exposed to danger.

# Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

\#Prerequisites What things you need to install and how will you install them:

\1.    Java 1.8 or later.

\2.    Maven 3.6.3 (It is optional since the maven wrapper is present in the project)

\3.    Postman 7 is optional. The application can be used as well with a browser.

 \#Installing A step by step series of examples that tell you how to get a development environment:

1.Install Java:

https://docs.oracle.com/javase/8/docs/technotes/guides/install/install_overview.html

2.Install Maven:

https://maven.apache.org/install.html

3.Install Postman:

https://learning.postman.com/docs/getting-started/installation-and-updates/

\#Running App

After installing all the required software, you will be ready to import the code into your favourite IDE and run the SafetyNetAlertsApplication.java to launch the application.

# Firestation

## GET

To retrieve all fire stations

### Syntax

http://localhost:8080/fireStation

#### Parameters

None

#### Example

```json
[   
  {
    "address": "1509 Culver St",
    "station": 3
  }
]
```

### Notes

API will return the Json and a 200 OK code if success

## POST

You can add a new fire station

### Syntax

http://localhost:8080/fireStation 

#### Parameters

Address is mandatory 

Station is mandatory 

#### Example

```json
{
  "address": "1509 Culver St",
  "station": "3"
}
```

### Notes

API will return a 204 No Content code if success

API will return a 400 Bad Request code if you didn’t provide all the element

### PUT

Update an existing fire station 

### Syntax

[http://localhost:8080/fireStation](http://localhost:8080/firestation)

#### Parameters

Address is mandatory to update the station id

Station is mandatory to be updated

#### Example

```json
{
  "address": "1509 Culver St",
  "station": "3"
}
```

### Notes

API will return a 201 Created code if success

API will return a 422 Unprocessable Entity with message “Address was unknown”, if the fire station is not found

API will return a 400 Bad Request code if you didn’t provide all the element

### DELETE

Delete an existing fire station 

### Syntax

[http://localhost:8080/fireStation](http://localhost:8080/firestation)

#### Parameters

Address OR station are mandatory to delete the station

#### Example

```Json
{
  "address": "1509 Culver St",
  "station": "3"
}
```

### Notes

API will return a 200 Ok code if success

API will return a 422 Unprocessable Entity with message “Address was unknown”, if the fire station is not found

API will return a 400 Bad Request code if you didn’t provide all the element

# Person

## GET

To retrieve all person

### Syntax

http://localhost:8080/person 

#### Parameters

None

#### Example

```Json
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
```

### Notes

API will return the Json and a 200 OK code if success

## POST

You can add a new person

### Syntax

http://localhost:8080/person  

#### Parameters

All fields are mandatory

#### Example

```json
  {
    "firstName": "John",
    "lastName": "Boyd",
    "address": "1509 Culver St",
    "city": "Culver",
    "zip": "97451",
    "phone": "841-874-6512",
    "email": "jaboyd@email.com"
  }
```

### Notes

API will return a 204 No Content code if success

API will return a 400 Bad Request code if you didn’t provide all the element

### PUT

Update an existing person 

### Syntax

http://localhost:8080/person 

#### Parameters

First Name and Last Name are mandatory to find the person to modify

All other fields are also mandatory

#### Example

```json
  {
    "firstName": "John",
    "lastName": "Boyd",
    "address": "1509 Culver St",
    "city": "Culver",
    "zip": "97451",
    "phone": "841-874-6512",
    "email": "jaboyd@email.com"
  }
```

###  Notes

API will return a 201 Created code if success

API will return a 422 Unprocessable Entity if the person is not found

API will return a 400 Bad Request code if you didn’t provide all the element

### DELETE

Delete an existing person

### Syntax

http://localhost:8080/person 

#### Parameters

firstName and lastName are mandatory to process request

#### Example

```json
{
	"firstName": "John",
	"lastName": "Boyd",
}
```

### Notes

API will return a 200 Ok code if success

API will return a 422 Unprocessable Entity if the person is not found

API will return a 400 Bad Request code if you didn’t provide all the element

# Medical record

## GET

To retrieve all medical record

### Syntax

[http://localhost:8080/medicalrecord ](http://localhost:8080/medicalrecord)

#### Parameters

None

#### Example

```JSON
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
```

### Notes

API will return the Json and a 200 OK code if success

## POST

You can add a new medical record

### Syntax

[http://localhost:8080/medicalrecord](http://localhost:8080/medicalrecord) 

#### Parameters

All fields are mandatory

#### Example

```json
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
```

### Notes

API will return a 204 No Content code if success

API will return a 400 Bad Request code if you didn’t provide all the element

### PUT

Update an existing person 

### Syntax

[http://localhost:8080/medicalrecord](http://localhost:8080/medicalrecord) 

#### Parameters

First Name and Last Name are mandatory to find the person to modify

All other fields are also mandatory

#### Example

```json
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
```

###  Notes

API will return a 201 Created code if success

API will return a 422 Unprocessable Entity if the medical record is not found

API will return a 400 Bad Request code if you didn’t provide all the element

### DELETE

- Delete an existing medical record

### Syntax

[http://localhost:8080/medicalrecord](http://localhost:8080/medicalrecord) 

#### Parameters

firstName and lastName are mandatory to process request

#### Exemple

```json
{
    "firstName": "John",
    "lastName": "Boyd",
}
```

### Notes

API will return a 200 Ok code if success

API will return a 422 Unprocessable Entity if the medical record is not found

API will return a 400 Bad Request code if you didn’t provide all the element

# URLS

## GET

firestation/stationNumber

### Syntax

[http://localhost:8080/fireStation/stationNumber?id=<fire_station_id>](http://localhost:8080/fireStation/stationNumber?id=1)

#### Parameters

The number of the station number

####  Example

```json
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
```

### Notes

The URL will return a list of persons that are covered by the station ID provided. The list will return first name, last name, address and phone. Moreover, it will include the number of adult and child (under 18) from the covered area.

## GET

/childAlert

### Syntax

[http://localhost:8080/childAlert?address=<address>](http://localhost:8080/childAlert?address=1509 Culver St)

#### Parameters

The address of the children

####  Example

```json
{
    "child": [
        {
            "firstName": "Tenley",
            "lastName": "Boyd",
            "age": 7
        },
        {
            "firstName": "Roger",
            "lastName": "Boyd",
            "age": 3
        }
    ],
    "familyMember": [
        {
            "firstName": "John",
            "lastName": "Boyd"
        },
        {
            "firstName": "Jacob",
            "lastName": "Boyd"
        },
        {
            "firstName": "Felicia",
            "lastName": "Boyd"
        }
    ]
}
```

### Notes

The API will return  a list of children with their age, first name and last name. It will include the names of the inhabitant of the address.

## GET

/phoneAlert

### Syntax

[http://localhost:8080/phoneAlert?firestation=<id>](http://localhost:8080/phoneAlert?firestation=3)

#### Parameters

the ID of the fire station

####  Example

```json
{
    "phoneNumber": [
        "841-874-6512",
        "841-874-6513",
        "841-874-6512",
        "841-874-6512",
        "841-874-6544",
        "841-874-6512",
        "841-874-6544",
        "841-874-6741",
        "841-874-6874",
        "841-874-8888",
        "841-874-9888",
        "841-874-6544",
        "841-874-6741"
    ]
}
```

### Notes

The API will return  a list of phone numbers that are covered by the station ID put as parameter.

## GET

/fire

### Syntax

[http://localhost:8080/fire?address=<address>](http://localhost:8080/fire?address=951 LoneTree Rd)

#### Parameters

The address of the fire station

####  Example

```json
{
    "station": 2,
    "inhabitant": [
        {
            "firstName": "Eric",
            "lastName": "Cadigan",
            "phone": "841-874-7458",
            "age": 75,
            "medications": [
                "tradoxidine:400mg"
            ]
        }
    ]
}
```

### Notes

The API will return the fire station ID covering the habitation. It will also include the list of inhabitant with the names, phone, age and the medication they are under.

## GET

/flood/stations

### Syntax

[http://localhost:8080/flood/stations?stations=<list of fire station ID>](http://localhost:8080/flood/stations?stations=1,2)

#### Parameters

a list of ID as integers

####  Example

```json
[
    {
        "address": "644 Gershwin Cir",
        "inhabitant": [
            {
                "firstName": "Peter",
                "lastName": "Duncan",
                "phone": "841-874-6512",
                "age": 20,
                "medications": []
            }
        ]
    }
]
```

### Notes

The API will return a list of addresses that are covered by the fire station with the list of inhabitant inside.

## GET

/personInfo

### Syntax

[http://localhost:8080/personInfo?firstName=<firstName>&lastName=<lastName>](http://localhost:8080/personInfo?firstName=John&lastName=Boyd)

#### Parameters

the first name and the last name of a person

####  Example

```json
[
    {
        "firstName": "John",
        "lastName": "Boyd",
        "address": "1509 Culver St",
        "age": 36,
        "email": "jaboyd@email.com",
        "medication": [
            "aznol:350mg",
            "hydrapermazol:100mg"
        ]
    }
]
```

### Notes

The API will return a list of person with homonyms. It will include the names, address, age, email and the medications of the person.

## GET

/communityEmail

### Syntax

[http://localhost:8080/communityEmail?city=<city>](http://localhost:8080/communityEmail?city=Culver)

#### Parameters

The name of the city

####  Example

```json
{
    "email": [
        "jaboyd@email.com",
        "drk@email.com",
        "tenz@email.com",
        "jaboyd@email.com",
        "jaboyd@email.com",
        "drk@email.com",
    ]
}
```

### Notes

The API will return a list of all the emails of the person living in the city put as parameter

# Testing

Use either mvn test if you have maven on your machine or ./mvnw test if you do not have maven on your machine.

To get all the different reports, you can use either mvn site or ./mvnw site

You can find the generated test reports in the target directory.

 