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

Requests can be made to get the following resources:

- Stock
- Intraday Time Series (interval: 1min, 5min, 15min, 30min, 60min)
- Daily Time Series
- Weekly Time Series
- Monthly Time Series

### Get Stock

#### Request

```
curl --location --request GET 'localhost:8080/api/v1/stock/get-stock' \
--header 'Content-Type: application/json' \
--data '{
    "symbol": "NVDA"
}'
```

#### Response

```
{
    "symbol": "NVDA",
    "open": 136.6900,
    "high": 138.5000,
    "low": 135.4649,
    "price": 137.7100,
    "volume": 201188760,
    "latestTradingDay": "2025-01-17",
    "previousClose": 133.5700,
    "change": 4.1400,
    "changePercent": 3.0995
}
```

### Get Intraday Time Series

#### Request

```
curl --location --request GET 'localhost:8080/api/v1/stock/get-intraday-ts' \
--header 'Content-Type: application/json' \
--data '{
    "symbol": "NVDA",
    "interval": "60min"
}'
```

#### Response

```

```

### Get Daily Time Series

#### Request

```
curl --location --request GET 'localhost:8080/api/v1/stock/get-daily-ts' \
--header 'Content-Type: application/json' \
--data '{
    "symbol": "NVDA"
}'
```

#### Response

```

```

### Get Weekly Time Series

#### Request

```
curl --location --request GET 'localhost:8080/api/v1/stock/get-weekly-ts' \
--header 'Content-Type: application/json' \
--data '{
    "symbol": "NVDA"
}'
```

#### Response

```

```

### Get Monthly Time Series

#### Request

```
curl --location --request GET 'localhost:8080/api/v1/stock/get-monthly-ts' \
--header 'Content-Type: application/json' \
--data '{
    "symbol": "NVDA"
}'
```

#### Response

```

```
