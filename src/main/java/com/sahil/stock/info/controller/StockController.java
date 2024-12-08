package com.sahil.stock.info.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sahil.stock.info.model.Stock;
import com.sahil.stock.info.service.StockService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("api/v1/stock")
@Slf4j
public class StockController {
    @Autowired
    private StockService stockService;

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public String home() {
        log.info("Received request to GET /home.");
        return String.format(
                "Stock Info Service%n%n" +
                        "Welcome to the stock endpoint, you can make the following requests:%n" +
                        "- POST /new-stock%n" +
                        "- GET /get-stock%n" +
                        "- DELETE /delete-stock%n");
    }

    @PostMapping(value = "/new-stock", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Stock> createStock(@RequestBody String name) {
        log.info("Received request to POST /new-stock with argument: " + name.trim());
        Stock stock = stockService.createStock(name.trim());
        if (stock != null) {
            return ResponseEntity.ok(stock);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @GetMapping(value = "/get-stock", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Stock> getStock(@RequestBody String name) {
        log.info("Received request to GET /get-stock with argument: " + name.trim());
        Stock stock = stockService.getStock(name.trim());
        if (stock != null) {
            return ResponseEntity.ok(stock);            
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping(value = "/delete-stock", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteStock(@RequestBody String name) {
        log.info("Received request to DELETE /delete-stock with argument: " + name.trim());
        boolean response = stockService.deleteStock(name.trim());
        if (response) {
            return ResponseEntity.ok("Stock deleted successfully.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
