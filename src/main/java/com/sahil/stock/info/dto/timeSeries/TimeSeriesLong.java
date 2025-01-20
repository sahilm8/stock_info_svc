package com.sahil.stock.info.dto.timeSeries;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TimeSeriesLong {
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
