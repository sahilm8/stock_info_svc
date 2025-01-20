package com.sahil.stock.info.dto;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetStockResponse {
    private String symbol;
    private BigDecimal open;
    private BigDecimal high;
    private BigDecimal low;
    private BigDecimal price;
    private BigDecimal volume;
    private Date latestTradingDay;
    private BigDecimal previousClose;
    private BigDecimal change;
    private BigDecimal changePercent;
}
