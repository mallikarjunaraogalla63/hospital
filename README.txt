# Spring Boot Project README

## Introduction
This Spring Boot project provides REST APIs for managing treatments, patients, and medicines. It allows users to create treatments, get treatments by ID, and includes proper validation and error handling.

## Technology Stack
- Spring Boot 2.x.x
- Java 18+
- JUnit 5
- Maven
- Hibernate
- Spring Data JPA
- MySQL/PostgreSQL



API Endpoints

Create Treatment
URL: /api/treatments
Method: POST
Request Body:
json
{
  "advice": "Take medicine with water",
  "medicine": {
    "id": 1
  },
  "patient": {
    "id": 1
  }
}
Response: Status 201 Created with created treatment details.

Get Treatment by ID
URL: /api/treatments/{id}
Method: GET
Response: Status 200 OK with treatment details.