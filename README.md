# Stock Info API

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white) ![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white) ![MySQL](https://img.shields.io/badge/mysql-4479A1.svg?style=for-the-badge&logo=mysql&logoColor=white) ![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)

![Maintenance](https://img.shields.io/badge/Maintained%3F-yes-green.svg)

![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)

API to fetch and return stock data.

## Stack

- Java 21
- Maven
- Spring Boot
- Spring Web
- Spring Webflux
- Reactor Core
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

Requests can be made to get the following resources:

- Stock
    - Global Quote
- Stock Time Series Intraday
    - Adjusted
    - Including extended hours
    - Intervals: 1min, 5min, 15min, 30min, 60min
- Stock Time Series Daily
    - Not adjusted
- Stock Time Series Weekly
    - Adjusted
- Stock Time Series Monthly
    - Adjusted

### Requests

- GET /:
```
curl -i -X GET http://localhost:8080/api/v1/stock/
```

- GET /get-global-quote: 
```
curl -i -X GET "http://localhost:8080/api/v1/stock/get-global-quote?symbol=nvda"
```

- GET /get-intraday:
```
curl -i -X GET "http://localhost:8080/api/v1/stock/get-intraday?symbol=nvda&interval=5min"
```

- GET /get-daily:
```
curl -i -X GET "http://localhost:8080/api/v1/stock/get-daily?symbol=nvda"
```

- GET /get-weekly:
```
curl -i -X GET "http://localhost:8080/api/v1/stock/get-weekly?symbol=nvda"
```

- GET /get-monthly:
```
curl -i -X GET "http://localhost:8080/api/v1/stock/get-monthly?symbol=nvda"
```
