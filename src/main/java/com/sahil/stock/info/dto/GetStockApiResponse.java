package com.sahil.stock.info.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetStockApiResponse {
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
    private Stock stock;

    @Data
    public static class Stock {
        @JsonProperty("01. symbol")
        private String symbol;

        @JsonProperty("02. open")
        private String open;

        @JsonProperty("03. high")
        private String high;

        @JsonProperty("04. low")
        private String low;

        @JsonProperty("05. price")
        private String price;

        @JsonProperty("06. volume")
        private String volume;

        @JsonProperty("07. latest trading day")
        private Date latestTradingDay;

        @JsonProperty("08. previous close")
        private String previousClose;

        @JsonProperty("09. change")
        private String change;

        @JsonProperty("10. change percent")
        private String changePercent;
    }
}
