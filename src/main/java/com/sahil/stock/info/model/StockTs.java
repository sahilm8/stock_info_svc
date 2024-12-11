package com.sahil.stock.info.model;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class StockTs {
    private BigDecimal open;
    private BigDecimal high;
    private BigDecimal low;
    private BigDecimal close;
    private BigDecimal volume;
}
