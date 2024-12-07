# Microservice Template

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white) ![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white) ![MySQL](https://img.shields.io/badge/mysql-4479A1.svg?style=for-the-badge&logo=mysql&logoColor=white) ![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)

![Maintenance](https://img.shields.io/badge/Maintained%3F-yes-green.svg)

![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)

Microservice template with Java 21, Maven, Spring Boot, Spring Web, Spring Data JPA, MySQL Connector, Docker, Spring Dotenv, Lombok, Spring Test, and H2 Database.

## Stack

- Java 21
- Maven
- Spring Boot
- Spring Web
- Spring Data JPA
- MySQL Connector
- Docker
- Spring Dotenv
- Lombok
- Spring Test
- H2 Database

## Endpoints

Instances can be created, fetched, or deleted for the default model class whose data is stored in a Docker MySQL container:
- POST /new-model: 
```
curl -i -X POST -H "Content-Type: application/json" -d "Model Name" http://localhost:8080/api/v1/model/new-model
```
- GET /get-model:
```
curl -i -X GET -H "Content-Type: application/json" -d "Model Name" http://localhost:8080/api/v1/model/get-model
```
- DELETE /delete-model:
```
curl -i -X DELETE -H "Content-Type: application/json" -d "Model Name" http://localhost:8080/api/v1/model/delete-model
```

## Setup

- Install dependencies:
```
./mvnw clean install
```
- Pull Docker MySQL image for running database server:
```
docker pull mysql:latest
```
- Create an external volume for storing MySQL data:
```
docker volume create mysql_volume
```
- Run the container:
```
docker compose up -d
```
- Start the application:
```
./mvnw spring-boot:run
```
- Stop the container:
```
docker compose down
```
