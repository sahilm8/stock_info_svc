package com.sahil.stock.info.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sahil.stock.info.model.Stock;
import com.sahil.stock.info.model.TimeSeries;
import com.sahil.stock.info.service.StockService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/v1/stock")
@Slf4j
public class StockController {
    @Autowired
    private StockService stockService;

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public String home() {
        log.info("Received request to GET /.");
        return String.format(
                "Stock Info Service%n%n" +
                        "Welcome to the stock endpoint, you can make the following requests:%n" +
                        "- GET /get-global-quote%n" +
                        "- GET /get-intraday%n" +
                        "- GET /get-daily%n" +
                        "- GET /get-weekly%n" +
                        "- GET /get-monthly%n");
    }

    @GetMapping(value = "/get-global-quote", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<Stock>> getGlobalQuote(@RequestParam String symbol) {
        log.info("Received request to GET /get-global-quote with argument: " + symbol.trim());
        return stockService.getGlobalQuote(symbol.trim()).map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping(value = "/get-intraday", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<TimeSeries>> getIntraday(@RequestParam String symbol, @RequestParam String interval) {
        log.info("Received request to GET /get-intraday with argument: " + symbol.trim() + ", " + interval.trim());
        return stockService.getTimeSeriesIntraday(symbol.trim(), interval.trim()).map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping(value = "/get-daily", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<TimeSeries>> getIntraday(@RequestParam String symbol) {
        log.info("Received request to GET /get-daily with argument: " + symbol.trim());
        return stockService.getTimeSeriesDaily(symbol.trim()).map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
