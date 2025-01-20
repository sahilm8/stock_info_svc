package com.sahil.stock.info.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sahil.stock.info.dto.getDailyTs.GetDailyTsRequest;
import com.sahil.stock.info.dto.getDailyTs.GetDailyTsResponse;
import com.sahil.stock.info.dto.getIntradayTs.GetIntradayTsRequest;
import com.sahil.stock.info.dto.getIntradayTs.GetIntradayTsResponse;
import com.sahil.stock.info.dto.getMonthlyTs.GetMonthlyTsRequest;
import com.sahil.stock.info.dto.getMonthlyTs.GetMonthlyTsResponse;
import com.sahil.stock.info.dto.getStock.GetStockRequest;
import com.sahil.stock.info.dto.getStock.GetStockResponse;
import com.sahil.stock.info.dto.getWeeklyTs.GetWeeklyTsRequest;
import com.sahil.stock.info.dto.getWeeklyTs.GetWeeklyTsResponse;
import com.sahil.stock.info.service.StockService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/stock")
@RequiredArgsConstructor
@Validated
public class StockController {
    private final StockService stockService;

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public String home() {
        return String.format(
                "Stock Info API%n%n" +
                        "Welcome to the /stock endpoint, you can make the following requests:%n" +
                        "- GET /get-stock%n" +
                        "- GET /get-intraday-ts%n" +
                        "- GET /get-daily-ts%n" +
                        "- GET /get-weekly-ts%n" +
                        "- GET /get-monthly-ts%n");
    }

    @GetMapping(value = "/get-stock", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Mono<GetStockResponse>> getStock(@Valid @RequestBody GetStockRequest getStockRequest) {
        return ResponseEntity.ok(stockService.getStock(getStockRequest));
    }

    @GetMapping(value = "/get-intraday-ts", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Mono<GetIntradayTsResponse>> getIntradayTs(
            @Valid @RequestBody GetIntradayTsRequest getIntradayTsRequest) {
        return ResponseEntity.ok(stockService.getIntradayTs(getIntradayTsRequest));
    }

    @GetMapping(value = "/get-daily-ts", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Mono<GetDailyTsResponse>> getDailyTs(
            @Valid @RequestBody GetDailyTsRequest getDailyTsRequest) {
        return ResponseEntity.ok(stockService.getDailyTs(getDailyTsRequest));
    }

    @GetMapping(value = "/get-weekly-ts", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Mono<GetWeeklyTsResponse>> getWeeklyTs(
            @Valid @RequestBody GetWeeklyTsRequest getWeeklyTsRequest) {
        return ResponseEntity.ok(stockService.getWeeklyTs(getWeeklyTsRequest));
    }

    @GetMapping(value = "/get-monthly-ts", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Mono<GetMonthlyTsResponse>> getMonthlyTs(
            @Valid @RequestBody GetMonthlyTsRequest getMonthlyTsRequest) {
        return ResponseEntity.ok(stockService.getMonthlyTs(getMonthlyTsRequest));
    }

    @GetMapping(value = "/*", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> invalid() {
        return ResponseEntity
                .ok("Invalid request, please refer to the README at https://github.com/sahilm8/stock_info_svc");
    }
}
