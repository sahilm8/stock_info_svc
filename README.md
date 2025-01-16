# Stock Info API

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white) ![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)

![Maintenance](https://img.shields.io/badge/Maintained%3F-yes-green.svg)

![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)

API to fetch and return stock data. Based on the [Alpha Vantage API](https://www.alphavantage.co).

## Stack

- Java 21
- Maven
- Spring Boot
- Spring Web
- Spring Webflux
- Reactor Core
- Spring Validation
- Jakarta Validation
- Spring Dotenv
- Lombok
- Spring Test
- MacOS DNS Resolver

## Setup

- Install dependencies:

```
./mvnw clean install
```

- Start the application:

```
./mvnw spring-boot:run
```

## Endpoints

Instances can be created, fetched, or deleted for the default model class that is stored in a Docker MySQL volume.

### Add Model

#### Request

```
curl --location 'localhost:8080/api/v1/model/add-model' \
--header 'Content-Type: application/json' \
--data '{
    "name": "Test User",
    "description": "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
}'
```

#### Response

```
{
    "model": {
        "id": 1,
        "name": "Test User",
        "description": "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
        "createdAt": "2025-01-15T08:20:33.355+00:00"
    }
}
```

### Get Model

#### Request

```
curl --location --request GET 'localhost:8080/api/v1/model/get-model' \
--header 'Content-Type: application/json' \
--data '{
    "name": "Test User"
}'
```

#### Response

```
{
    "model": {
        "id": 1,
        "name": "Test User",
        "description": "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
        "createdAt": "2025-01-15T08:20:33.355+00:00"
    }
}
```

### Delete Model

#### Request

```
curl --location --request DELETE 'localhost:8080/api/v1/model/delete-model' \
--header 'Content-Type: application/json' \
--data '{
    "name": "Test User"
}'
```

#### Response

```
{
    "status": "Model deleted successfully"
}
```
