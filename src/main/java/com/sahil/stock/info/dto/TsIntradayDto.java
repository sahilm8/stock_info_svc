package com.sahil.stock.info.dto;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class TsIntradayDto {
    /*
    API Response:
    {
        "Meta Data": {
            "1. Information": "Intraday (15min) open, high, low, close prices and volume",
            "2. Symbol": "AAPL",
            "3. Last Refreshed": "2024-12-10 19:45:00",
            "4. Interval": "15min",
            "5. Output Size": "Compact",
            "6. Time Zone": "US/Eastern"
        },
        "Time Series (15min)": {
            "2024-12-10 19:45:00": {
                "1. open": "247.6200",
                "2. high": "247.7400",
                "3. low": "247.5500",
                "4. close": "247.6900",
                "5. volume": "2454"
            },
            "2024-12-10 19:30:00": {
                "1. open": "247.7400",
                "2. high": "247.7400",
                "3. low": "247.5100",
                "4. close": "247.6200",
                "5. volume": "9980"
            },
            "2024-12-10 19:15:00": {
                "1. open": "247.6600",
                "2. high": "247.7400",
                "3. low": "247.6100",
                "4. close": "247.6300",
                "5. volume": "1088"
            }
        }
    }
    */
        
    private String interval;

    public TsDailyDto(interval) {
        this.interval = interval;
    }
    
    interval.equals("Time Series (1min)")

    @JsonProperty(interval.e)
    private Map<String, Map<String, String>> timeSeries = new HashMap<>();

    @JsonProperty("1. open")
    public String getOpen(String timestamp) {
        return timeSeries.get(timestamp).get("1. open");
    }
    
    @JsonProperty("2. high")
    public String getHigh(String timestamp) {
        return timeSeries.get(timestamp).get("2. high");
    }
    
    @JsonProperty("3. low")
    public String getLow(String timestamp) {
        return timeSeries.get(timestamp).get("3. low");
    }
    
    @JsonProperty("4. close")
    public String getClose(String timestamp) {
        return timeSeries.get(timestamp).get("4. close");
    }
    
    @JsonProperty("5. volume")
    public String getVolume(String timestamp) {
        return timeSeries.get(timestamp).get("5. volume");
    }
}
