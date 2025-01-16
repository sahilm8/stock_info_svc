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
public class GetStockResponse {
    /*
     * API Response:
     * {
     * "Global Quote": {
     * "01. symbol": "IBM",
     * "02. open": "234.4300",
     * "03. high": "238.3800",
     * "04. low": "234.2200",
     * "05. price": "238.0400",
     * "06. volume": "4028430",
     * "07. latest trading day": "2024-12-06",
     * "08. previous close": "234.7500",
     * "09. change": "3.2900",
     * "10. change percent": "1.4015%"
     * }
     * }
     */

    @JsonProperty("Global Quote")
    private Map<String, String> globalQuote;

    @JsonProperty("symbol")
    public String getSymbol() {
        return globalQuote.get("01. symbol");
    }

    @JsonProperty("open")
    public String getOpen() {
        return globalQuote.get("02. open");
    }

    @JsonProperty("high")
    public String getHigh() {
        return globalQuote.get("03. high");
    }

    @JsonProperty("low")
    public String getLow() {
        return globalQuote.get("04. low");
    }

    @JsonProperty("price")
    public String getPrice() {
        return globalQuote.get("05. price");
    }

    @JsonProperty("volume")
    public String getVolume() {
        return globalQuote.get("06. volume");
    }

    @JsonProperty("latestTradingDay")
    public String getLatestTradingDay() {
        return globalQuote.get("07. latest trading day");
    }

    @JsonProperty("previousClose")
    public String getPrevClose() {
        return globalQuote.get("08. previous close");
    }

    @JsonProperty("change")
    public String getChange() {
        return globalQuote.get("09. change");
    }

    @JsonProperty("changePercent")
    public String getChangePercent() {
        return globalQuote.get("10. change percent");
    }
}
