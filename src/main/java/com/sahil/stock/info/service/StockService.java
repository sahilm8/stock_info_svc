package com.sahil.stock.info.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import com.sahil.stock.info.util.ApiFunctions;

import jakarta.annotation.PostConstruct;

import com.sahil.stock.info.dto.GetIntradayTsRequest;
import com.sahil.stock.info.dto.GetIntradayTsResponse;
import com.sahil.stock.info.dto.GetMonthlyTsRequest;
import com.sahil.stock.info.dto.GetStockRequest;
import com.sahil.stock.info.dto.GetStockResponse;
import com.sahil.stock.info.dto.GetWeeklyTsRequest;
import com.sahil.stock.info.dto.GetDailyTsRequest;
import com.sahil.stock.info.dto.GetDailyTsResponse;
import com.sahil.stock.info.dto.GetMonthlyTsResponse;
import com.sahil.stock.info.dto.GetWeeklyTsResponse;

import reactor.core.publisher.Mono;

@Service
public class StockService {
    @Value("${stock.api-url}")
    private String apiUrl;

    @Value("${stock.api-key}")
    private String apiKey;

    private WebClient webClient;

    @PostConstruct
    public void init() {
        ExchangeStrategies strategies = ExchangeStrategies
                .builder()
                .codecs(configurer -> configurer
                        .defaultCodecs()
                        .maxInMemorySize(1024 * 1024 * 10) // 10MB
                )
                .build();

        webClient = WebClient
                .builder()
                .baseUrl(apiUrl)
                .exchangeStrategies(strategies)
                .build();
    }

    public Mono<GetStockResponse> getStock(GetStockRequest getStockRequest) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/query")
                        .queryParam("function", ApiFunctions.GLOBAL_QUOTE.getValue())
                        .queryParam("symbol", getStockRequest.getSymbol())
                        // datatype: json or csv
                        .queryParam("datatype", "json")
                        .queryParam("apikey", apiKey)
                        .build())
                .retrieve()
                .bodyToMono(GetStockResponse.class);
    }

    public Mono<GetIntradayTsResponse> getIntradayTs(GetIntradayTsRequest getIntradayTsRequest) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/query")
                        .queryParam("function", ApiFunctions.TIME_SERIES_INTRADAY.getValue())
                        .queryParam("symbol", getIntradayTsRequest.getSymbol())
                        .queryParam("interval", getIntradayTsRequest.getInterval())
                        .queryParam("adjusted", true)
                        .queryParam("extended_hours", true)
                        // outputsize: compact (last 100 data points) or full (last 30 days data points)
                        .queryParam("outputsize", "compact")
                        // datatype: json or csv
                        .queryParam("datatype", "json")
                        .queryParam("apikey", apiKey)
                        .build())
                .retrieve()
                .bodyToMono(GetIntradayTsResponse.class);
    }

    public Mono<GetDailyTsResponse> getDailyTs(GetDailyTsRequest getDailyTsRequest) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/query")
                        .queryParam("function", ApiFunctions.TIME_SERIES_DAILY.getValue())
                        .queryParam("symbol", getDailyTsRequest.getSymbol())
                        // outputsize: compact (last 100 data points) or full (last 20+ years data
                        // points)
                        .queryParam("outputsize", "compact")
                        // datatype: json or csv
                        .queryParam("datatype", "json")
                        .queryParam("apikey", apiKey)
                        .build())
                .retrieve()
                .bodyToMono(GetDailyTsResponse.class);
    }

    public Mono<GetWeeklyTsResponse> getWeeklyTs(GetWeeklyTsRequest getWeeklyTsRequest) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/query")
                        .queryParam("function", ApiFunctions.TIME_SERIES_WEEKLY_ADJUSTED.getValue())
                        .queryParam("symbol", getWeeklyTsRequest.getSymbol())
                        // datatype: json or csv
                        .queryParam("datatype", "json")
                        .queryParam("apikey", apiKey)
                        .build())
                .retrieve()
                .bodyToMono(GetWeeklyTsResponse.class);
    }

    public Mono<GetMonthlyTsResponse> getMonthlyTs(GetMonthlyTsRequest getMonthlyTsRequest) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/query")
                        .queryParam("function", ApiFunctions.TIME_SERIES_MONTHLY_ADJUSTED.getValue())
                        .queryParam("symbol", getMonthlyTsRequest.getSymbol())
                        // datatype: json or csv
                        .queryParam("datatype", "json")
                        .queryParam("apikey", apiKey)
                        .build())
                .retrieve()
                .bodyToMono(GetMonthlyTsResponse.class);
    }
}
