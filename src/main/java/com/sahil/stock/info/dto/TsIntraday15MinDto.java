package com.sahil.stock.info.dto;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class TsIntraday15MinDto extends TsIntradayDto {
    @JsonProperty("Time Series (15min)")
    private Map<String, Map<String, String>> timeSeries = new HashMap<>();
}
