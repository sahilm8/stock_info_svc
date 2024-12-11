package com.sahil.stock.info.model;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

@Data
public class TimeSeries {
    private Map<String, StockTs> timeSeries = new HashMap<>();
}
