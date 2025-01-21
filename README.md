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

Requests can be made to perform the following actions:

- Get Stock
- Get Intraday Time Series (interval: 1min, 5min, 15min, 30min, 60min)
- Get Daily Time Series
- Get Weekly Time Series
- Get Monthly Time Series

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
{
    "timeSeries": {
        "2025-01-14 09:00:00": {
            "open": 135.8800,
            "high": 136.3800,
            "low": 133.3500,
            "close": 133.5044,
            "volume": 29463739
        },
        "2025-01-14 19:00:00": {
            "open": 131.3400,
            "high": 131.3900,
            "low": 131.2000,
            "close": 131.2999,
            "volume": 354662
        },
        "2025-01-17 12:00:00": {
            "open": 137.9800,
            "high": 138.0900,
            "low": 137.6300,
            "close": 137.7600,
            "volume": 13961753
        },
        ...
    }
}
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
{
    "timeSeries": {
        "2024-09-20": {
            "open": 117.0600,
            "high": 118.6181,
            "low": 115.3901,
            "close": 116.0000,
            "volume": 382462428
        },
        "2024-12-31": {
            "open": 138.0300,
            "high": 138.0700,
            "low": 133.8300,
            "close": 134.2900,
            "volume": 155659211
        },
        "2024-12-30": {
            "open": 134.8300,
            "high": 140.2700,
            "low": 134.0200,
            "close": 137.4900,
            "volume": 167734700
        },
        ...
    }
}
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
{
    "timeSeries": {
        "2020-08-07": {
            "open": 429.3000,
            "high": 460.1900,
            "low": 428.6100,
            "close": 447.9800,
            "adjustedClose": 11.1646,
            "volume": 39017130,
            "dividendAmount": 0.0000
        },
        "2011-04-15": {
            "open": 17.6200,
            "high": 18.9400,
            "low": 16.8300,
            "close": 18.7100,
            "adjustedClose": 0.4290,
            "volume": 109024400,
            "dividendAmount": 0.0000
        },
        "2001-07-06": {
            "open": 89.0800,
            "high": 90.7000,
            "low": 79.1000,
            "close": 80.7000,
            "adjustedClose": 0.3084,
            "volume": 13621800,
            "dividendAmount": 0.0000
        },
        ...
    }
}
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
{
    "timeSeries": {
        "2014-12-31": {
            "open": 20.8800,
            "high": 21.2500,
            "low": 19.1000,
            "close": 20.0500,
            "adjustedClose": 0.4813,
            "volume": 106485166,
            "dividendAmount": 0.0000
        },
        "2007-06-29": {
            "open": 35.0300,
            "high": 43.8700,
            "low": 34.4200,
            "close": 41.3100,
            "adjustedClose": 0.6315,
            "volume": 241007871,
            "dividendAmount": 0.0000
        },
        "2016-10-31": {
            "open": 68.5200,
            "high": 72.9500,
            "low": 63.7000,
            "close": 71.1600,
            "adjustedClose": 1.7503,
            "volume": 163789051,
            "dividendAmount": 0.0000
        },
        ...
    }
}
```
