package com.sahil.stock.info.dto;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetDailyTsResponse {
    private Map<String, TimeSeriesShort> timeSeries;
}
