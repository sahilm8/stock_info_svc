package com.sahil.stock.info.model;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

@Data
public class TimeSeriesAdjusted {
    private Map<String, StockTsAdjusted> timeSeries = new HashMap<>();
}
