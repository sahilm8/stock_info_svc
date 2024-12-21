package com.sahil.stock.info.dto;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class GlobalQuoteDto {
    /*
    API Response:
    {
        "Global Quote": {
        "01. symbol": "IBM",
        "02. open": "234.4300",
        "03. high": "238.3800",
        "04. low": "234.2200",
        "05. price": "238.0400",
        "06. volume": "4028430",
        "07. latest trading day": "2024-12-06",
        "08. previous close": "234.7500",
        "09. change": "3.2900",
        "10. change percent": "1.4015%"
        }
    }
    */

    @JsonProperty("Global Quote")
    private Map<String, String> globalQuote = new HashMap<>();

    public String getSymbol() {
        return globalQuote.get("01. symbol");
    }

    public String getOpen() {
        return new String(globalQuote.get("02. open"));
    }

    public String getHigh() {
        return new String(globalQuote.get("03. high"));
    }

    public String getLow() {
        return new String(globalQuote.get("04. low"));
    }

    public String getPrice() {
        return new String(globalQuote.get("05. price"));
    }

    public String getVolume() {
        return new String(globalQuote.get("06. volume"));
    }

    public String getLatestTradingDay() {
        return globalQuote.get("07. latest trading day");
    }

    public String getPrevClose() {
        return new String(globalQuote.get("08. previous close"));
    }

    public String getChange() {
        return new String(globalQuote.get("09. change"));
    }

    public String getChangePercent() {
        return new String(globalQuote.get("10. change percent"));
    }
}
