package com.sahil.stock.info.util;

public enum ApiFunctions {
    GLOBAL_QUOTE("GLOBAL_QUOTE");
    
    private final String value;
    
    ApiFunctions(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
