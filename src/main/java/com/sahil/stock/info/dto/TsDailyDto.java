package com.sahil.stock.info.dto;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

@Data
public class TsDailyDto {
    private Map<String, Map<String, String>> timeSeries = new HashMap<>();    
}
