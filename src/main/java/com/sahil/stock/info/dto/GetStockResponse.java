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
    @JsonProperty("Global Quote")
    private Map<String, String> globalQuote;

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
