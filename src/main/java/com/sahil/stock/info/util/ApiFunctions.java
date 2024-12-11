package com.sahil.stock.info.util;

public enum ApiFunctions {
    GLOBAL_QUOTE("GLOBAL_QUOTE"),
    TIME_SERIES_INTRADAY("TIME_SERIES_INTRADAY"),
    TIME_SERIES_DAILY("TIME_SERIES_DAILY");
    
    private final String value;
    
    ApiFunctions(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
