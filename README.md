# TRANSFER-SERVICE BANKING DEMO APPLICATION
A Transfer Service API example of a REST-ful WebServer developed using SpringBoot.

## Requirements

The fully fledged server uses the following:

* SpringBoot

## Dependencies
* There are a number of third-party dependencies used in the project. 
* Browse the Maven pom.xml file for details of libraries and versions used.

# Building the project
You will need:

1. Java JDK 8 or higher
2. Maven 3.1.1 or higher

Clone the project and use Maven to build the server

```
$ mvn clean install
```

## Details

This application is exposed to two different endpoints.

Fetch Account details

1. REST METHOD : POST
2. Media Type : JSON (Input & Output)
3. Body
    * accountNumber
    * requestId

  
Transfer funds

1. REST METHOD : POST
2. Media Type : JSON (Input & Output)
3. Body
    * requestId
    * sourceAccountNumber
    * destinationAccountNumber
    * transferAmount

## Unit test cases covered

* Transfer Success
* Same accountEntity transfer negative test
* Source accountEntity not found negative test
* Destination accountEntity not found negative test
* Insufficient balance
