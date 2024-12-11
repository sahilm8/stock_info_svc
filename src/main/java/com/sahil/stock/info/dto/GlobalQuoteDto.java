package com.sahil.stock.info.dto;

import java.math.BigDecimal;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class GlobalQuoteDto {
    /*
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
    public Map<String, String> globalQuote;

    public String getSymbol() {
        return globalQuote.get("01. symbol");
    }

    public BigDecimal getOpen() {
        return new BigDecimal(globalQuote.get("02. open"));
    }

    public BigDecimal getHigh() {
        return new BigDecimal(globalQuote.get("03. high"));
    }

    public BigDecimal getLow() {
        return new BigDecimal(globalQuote.get("04. low"));
    }

    public BigDecimal getPrice() {
        return new BigDecimal(globalQuote.get("05. price"));
    }

    public BigDecimal getVolume() {
        return new BigDecimal(globalQuote.get("06. volume"));
    }

    public BigDecimal getPrevClose() {
        return new BigDecimal(globalQuote.get("08. previous close"));
    }

    public BigDecimal getChange() {
        return new BigDecimal(globalQuote.get("09. change"));
    }

    public BigDecimal getChangePercent() {
        return new BigDecimal(globalQuote.get("10. change percent").replace("%", ""));
    }
}
