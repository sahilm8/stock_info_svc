package com.sahil.stock.info.model;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "Stock")
@Data
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String symbol;

    @Column(nullable = false)
    private BigDecimal open;

    @Column(nullable = false)
    private BigDecimal high;

    @Column(nullable = false)
    private BigDecimal low;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private BigDecimal volume;

    @Column(nullable = false)
    private BigDecimal prevClose;

    @Column(nullable = false)
    private BigDecimal change;

    @Column(nullable = false)
    private int changePercent;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Date fetchedAt;
}
