package com.sahil.stock.info.dto.getMonthlyTs;

import java.math.BigDecimal;
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
public class GetMonthlyTsApiResponse {
    /*
     * API Response:
     * {
     * "Meta Data": {
     * "1. Information": "Monthly Adjusted Prices and Volumes",
     * "2. Symbol": "IBM",
     * "3. Last Refreshed": "2024-12-18",
     * "4. Time Zone": "US/Eastern"
     * },
     * "Monthly Adjusted Time Series": {
     * "2024-12-18": {
     * "1. open": "230.7300",
     * "2. high": "231.0300",
     * "3. low": "220.0300",
     * "4. close": "220.1700",
     * "5. adjusted close": "220.1700",
     * "6. volume": "11068865",
     * "7. dividend amount": "0.0000"
     * },
     * "2024-12-13": {
     * "1. open": "238.0000",
     * "2. high": "239.3500",
     * "3. low": "227.8000",
     * "4. close": "230.8200",
     * "5. adjusted close": "230.8200",
     * "6. volume": "20886084",
     * "7. dividend amount": "0.0000"
     * },
     * "2024-12-06": {
     * "1. open": "227.5000",
     * "2. high": "238.3800",
     * "3. low": "225.5100",
     * "4. close": "238.0400",
     * "5. adjusted close": "238.0400",
     * "6. volume": "18743737",
     * "7. dividend amount": "0.0000"
     * },
     * ...
     * }
     * }
     */

    @JsonProperty("Monthly Adjusted Time Series")
    private Map<String, TimeSeries> timeSeries;

    @Data
    public static class TimeSeries {
        @JsonProperty("1. open")
        private BigDecimal open;

        @JsonProperty("2. high")
        private BigDecimal high;

        @JsonProperty("3. low")
        private BigDecimal low;

        @JsonProperty("4. close")
        private BigDecimal close;

        @JsonProperty("5. adjusted close")
        private BigDecimal adjustedClose;

        @JsonProperty("6. volume")
        private BigDecimal volume;

        @JsonProperty("7. dividend amount")
        private BigDecimal dividendAmount;
    }
}
