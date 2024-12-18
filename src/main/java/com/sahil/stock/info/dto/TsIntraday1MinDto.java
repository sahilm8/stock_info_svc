package com.sahil.stock.info.dto;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class TsIntraday1MinDto extends TsIntradayDto {
    @JsonProperty("Time Series (1min)")
    private Map<String, Map<String, String>> timeSeries = new HashMap<>();

    @Override
    public String getOpen(String timestamp) {
            return timeSeries.get(timestamp).get("1. open");
    }

    @Override
    public String getHigh(String timestamp) {
        return timeSeries.get(timestamp).get("2. high");
    }

    @Override
    public String getLow(String timestamp) {
        return timeSeries.get(timestamp).get("3. low");
    }

    @Override
    public String getClose(String timestamp) {
        return timeSeries.get(timestamp).get("4. close");
    }

    @Override
    public String getVolume(String timestamp) {
        return timeSeries.get(timestamp).get("5. volume");
    }
}
