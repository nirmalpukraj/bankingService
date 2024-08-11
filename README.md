# TRANSFER-SERVICE DEMO APPLICATION
A Transfer Service API example of a REST-ful WebServer developed using SpringBoot.

## Requirements

The fully fledged server uses the following:

SpringBoot

## Dependencies
* There are a number of third-party dependencies used in the project. 
* Browse the Maven pom.xml file for details of libraries and versions used.

# Building the project
You will need:

1. Java JDK 8 or higher
2. Maven 3.1.1 or higher
Clone the project and use Maven to build the server

`$ mvn clean install`

## Details

This application is exposed to two different endpoints.

GET Account details

REST METHOD : POST
Media Type : JSON (Input & Output)
* Body
* accountNumber
* requestId
* Transfer funds

REST METHOD : POST
Media Type : JSON (Input & Output)
Body
requestId
sourceAccountNumber
destinationAccountNumber
transferAmount

## Unit test cases covered

* Transfer Success
* Same accountEntity transfer negative test
* Source accountEntity not found negative test
* Destination accountEntity not found negative test
* Insufficient balance