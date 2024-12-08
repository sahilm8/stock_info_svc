package com.sahil.stock.info.dto;

import java.math.BigDecimal;

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
    @JsonProperty("01. symbol")
    private String symbol;

    @JsonProperty("02. open")
    private BigDecimal open;

    @JsonProperty("03. high")
    private BigDecimal high;

    @JsonProperty("04. low")
    private BigDecimal low;

    @JsonProperty("05. price")
    private BigDecimal price;

    @JsonProperty("06. volume")
    private BigDecimal volume;

    @JsonProperty("07. latest trading day")
    private String latestTradingDay;

    @JsonProperty("08. previous close")
    private BigDecimal prevClose;

    @JsonProperty("09. change")
    private BigDecimal change;

    @JsonProperty("10. change percent")
    private int changePercent;
}
