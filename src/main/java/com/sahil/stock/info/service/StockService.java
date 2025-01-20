package com.sahil.stock.info.service;

import java.math.BigDecimal;

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
import com.sahil.stock.info.dto.GetIntradayTsApiResponse;
import com.sahil.stock.info.dto.GetMonthlyTsResponse;
import com.sahil.stock.info.dto.GetStockApiResponse;
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
                                .bodyToMono(GetStockApiResponse.class)
                                .map((getStockApiResponse) -> {
                                        GetStockResponse.GetStockResponseBuilder builder = GetStockResponse
                                                        .builder();
                                        builder.symbol(getStockApiResponse.getStock().getSymbol());
                                        builder.open(new BigDecimal(getStockApiResponse.getStock().getOpen()));
                                        builder.high(new BigDecimal(getStockApiResponse.getStock().getHigh()));
                                        builder.low(new BigDecimal(getStockApiResponse.getStock().getLow()));
                                        builder.price(new BigDecimal(getStockApiResponse.getStock().getPrice()));
                                        builder.volume(new BigDecimal(getStockApiResponse.getStock().getVolume()));
                                        builder.latestTradingDay(getStockApiResponse.getStock().getLatestTradingDay());
                                        builder.previousClose(
                                                        new BigDecimal(
                                                                        getStockApiResponse.getStock()
                                                                                        .getPreviousClose()));
                                        builder.change(new BigDecimal(getStockApiResponse.getStock().getChange()));
                                        builder.changePercent(
                                                        new BigDecimal(
                                                                        getStockApiResponse.getStock()
                                                                                        .getChangePercent()
                                                                                        .split("%")[0]));

                                        return builder.build();
                                });
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
                                                // outputsize: compact (last 100 data points) or full (last 30 days data
                                                // points)
                                                .queryParam("outputsize", "compact")
                                                // datatype: json or csv
                                                .queryParam("datatype", "json")
                                                .queryParam("apikey", apiKey)
                                                .build())
                                .retrieve()
                                .bodyToMono(GetIntradayTsApiResponse.class)
                                .map((getIntradayTsApiResponse) -> {
                                        GetIntradayTsResponse.GetIntradayTsResponseBuilder builder = GetIntradayTsResponse
                                                        .builder();
                                        builder.timeSeries(getIntradayTsApiResponse.getTimeSeries());

                                        return builder.build();
                                });
        }

        public Mono<GetDailyTsResponse> getDailyTs(GetDailyTsRequest getDailyTsRequest) {
                return webClient.get()
                                .uri(uriBuilder -> uriBuilder
                                                .path("/query")
                                                .queryParam("function", ApiFunctions.TIME_SERIES_DAILY.getValue())
                                                .queryParam("symbol", getDailyTsRequest.getSymbol())
                                                // outputsize: compact (last 100 data points) or full (last 20+ years
                                                // data
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
                                                .queryParam("function",
                                                                ApiFunctions.TIME_SERIES_WEEKLY_ADJUSTED.getValue())
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
                                                .queryParam("function",
                                                                ApiFunctions.TIME_SERIES_MONTHLY_ADJUSTED.getValue())
                                                .queryParam("symbol", getMonthlyTsRequest.getSymbol())
                                                // datatype: json or csv
                                                .queryParam("datatype", "json")
                                                .queryParam("apikey", apiKey)
                                                .build())
                                .retrieve()
                                .bodyToMono(GetMonthlyTsResponse.class);
        }
}
