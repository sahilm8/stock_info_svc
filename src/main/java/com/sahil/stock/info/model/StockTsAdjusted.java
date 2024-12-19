package com.sahil.stock.info.model;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class StockTsAdjusted {
    private BigDecimal open;
    private BigDecimal high;
    private BigDecimal low;
    private BigDecimal close;
    private BigDecimal adjustedClose;
    private BigDecimal volume;
    private BigDecimal dividendAmount;
}
