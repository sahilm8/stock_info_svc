package com.sahil.stock.info.dto;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetDailyTsApiResponse {
    /*
     * API Response:
     * {
     * "Meta Data": {
     * "1. Information": "Daily Prices (open, high, low, close) and Volumes",
     * "2. Symbol": "IBM",
     * "3. Last Refreshed": "2024-12-18",
     * "4. Output Size": "Compact",
     * "5. Time Zone": "US/Eastern"
     * },
     * "Time Series (Daily)": {
     * "2024-12-18": {
     * "1. open": "229.0350",
     * "2. high": "229.0350",
     * "3. low": "220.0300",
     * "4. close": "220.1700",
     * "5. volume": "3807262"
     * },
     * "2024-12-17": {
     * "1. open": "229.2300",
     * "2. high": "230.2000",
     * "3. low": "227.6200",
     * "4. close": "228.9700",
     * "5. volume": "3651346"
     * },
     * "2024-12-16": {
     * "1. open": "230.7300",
     * "2. high": "231.0300",
     * "3. low": "226.8800",
     * "4. close": "229.3300",
     * "5. volume": "3610257"
     * },
     * ...
     * }
     * }
     */

    @JsonProperty("Time Series (Daily)")
    private Map<String, TimeSeriesShort> timeSeries;
}
