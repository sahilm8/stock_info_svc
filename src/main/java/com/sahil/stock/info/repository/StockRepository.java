package com.sahil.stock.info.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sahil.stock.info.model.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {
    public Optional<Stock> findBySymbol(String symbol);
}
