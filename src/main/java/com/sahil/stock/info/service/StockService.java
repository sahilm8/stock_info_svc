package com.sahil.stock.info.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sahil.stock.info.model.Stock;
import com.sahil.stock.info.repository.StockRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class StockService {
    @Autowired
    private StockRepository stockRepository;

    public Stock createStock(String name) {
        if (stockRepository.findByName(name).isEmpty()) {
            Stock stock = new Stock();
            // Set other details.
            stock.setName(name);
            stockRepository.save(stock);
            log.info("Stock created: " + stock.toString());
            return stock; 
        }
        Stock stock = stockRepository.findByName(name).get();
        log.info("Stock already exists: " + stock.toString());
        // Update it.
        return null;
    }

    public Stock getStock(String name) {
        if (stockRepository.findByName(name).isPresent()) {
            Stock stock = stockRepository.findByName(name).get();
            log.info("Stock found: " + stock.toString());
            return stock;
        }
        log.info("Stock not found: " + name);
        return null;
    }

    public boolean deleteStock(String name) {
        if (stockRepository.findByName(name).isPresent()) {
            Stock stock = stockRepository.findByName(name).get();
            stockRepository.delete(stock);
            log.info("Stock deleted: " + name);
            return true;
        }
        log.info("Stock not found: " + name);
        return false;
    }
}
