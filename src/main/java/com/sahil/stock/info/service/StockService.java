package com.sahil.stock.info.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import com.sahil.stock.info.util.ApiFunctions;

import jakarta.annotation.PostConstruct;

import com.sahil.stock.info.dto.getDailyTs.GetDailyTsApiResponse;
import com.sahil.stock.info.dto.getDailyTs.GetDailyTsRequest;
import com.sahil.stock.info.dto.getDailyTs.GetDailyTsResponse;
import com.sahil.stock.info.dto.getIntradayTs.GetIntradayTsApiResponse;
import com.sahil.stock.info.dto.getIntradayTs.GetIntradayTsRequest;
import com.sahil.stock.info.dto.getIntradayTs.GetIntradayTsResponse;
import com.sahil.stock.info.dto.getMonthlyTs.GetMonthlyTsApiResponse;
import com.sahil.stock.info.dto.getMonthlyTs.GetMonthlyTsRequest;
import com.sahil.stock.info.dto.getMonthlyTs.GetMonthlyTsResponse;
import com.sahil.stock.info.dto.getStock.GetStockApiResponse;
import com.sahil.stock.info.dto.getStock.GetStockRequest;
import com.sahil.stock.info.dto.getStock.GetStockResponse;
import com.sahil.stock.info.dto.getWeeklyTs.GetWeeklyTsApiResponse;
import com.sahil.stock.info.dto.getWeeklyTs.GetWeeklyTsRequest;
import com.sahil.stock.info.dto.getWeeklyTs.GetWeeklyTsResponse;

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
                                        builder.open(getStockApiResponse.getStock().getOpen());
                                        builder.high(getStockApiResponse.getStock().getHigh());
                                        builder.low(getStockApiResponse.getStock().getLow());
                                        builder.price(getStockApiResponse.getStock().getPrice());
                                        builder.volume(getStockApiResponse.getStock().getVolume());
                                        builder.latestTradingDay(getStockApiResponse.getStock().getLatestTradingDay());
                                        builder.previousClose(getStockApiResponse.getStock().getPreviousClose());
                                        builder.change(getStockApiResponse.getStock().getChange());
                                        builder.changePercent(new BigDecimal(getStockApiResponse.getStock()
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
                                        Map<String, GetIntradayTsResponse.TimeSeries> timeSeries = new HashMap<>();
                                        for (Map.Entry<String, GetIntradayTsApiResponse.TimeSeries> entry : getIntradayTsApiResponse
                                                        .getTimeSeries().entrySet()) {
                                                GetIntradayTsResponse.TimeSeries data = new GetIntradayTsResponse.TimeSeries();
                                                data.setOpen(entry.getValue().getOpen());
                                                data.setHigh(entry.getValue().getHigh());
                                                data.setLow(entry.getValue().getLow());
                                                data.setClose(entry.getValue().getClose());
                                                data.setVolume(entry.getValue().getVolume());
                                                timeSeries.put(entry.getKey(), data);
                                        }

                                        builder.timeSeries(timeSeries);

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
                                .bodyToMono(GetDailyTsApiResponse.class)
                                .map((getDailyTsApiResponse) -> {
                                        GetDailyTsResponse.GetDailyTsResponseBuilder builder = GetDailyTsResponse
                                                        .builder();
                                        Map<String, GetDailyTsResponse.TimeSeries> timeSeries = new HashMap<>();
                                        for (Map.Entry<String, GetDailyTsApiResponse.TimeSeries> entry : getDailyTsApiResponse
                                                        .getTimeSeries().entrySet()) {
                                                GetDailyTsResponse.TimeSeries data = new GetDailyTsResponse.TimeSeries();
                                                data.setOpen(entry.getValue().getOpen());
                                                data.setHigh(entry.getValue().getHigh());
                                                data.setLow(entry.getValue().getLow());
                                                data.setClose(entry.getValue().getClose());
                                                data.setVolume(entry.getValue().getVolume());
                                                timeSeries.put(entry.getKey(), data);
                                        }

                                        builder.timeSeries(timeSeries);

                                        return builder.build();
                                });
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
                                .bodyToMono(GetWeeklyTsApiResponse.class)
                                .map((getWeeklyTsApiResponse) -> {
                                        GetWeeklyTsResponse.GetWeeklyTsResponseBuilder builder = GetWeeklyTsResponse
                                                        .builder();
                                        Map<String, GetWeeklyTsResponse.TimeSeries> timeSeries = new HashMap<>();
                                        for (Map.Entry<String, GetWeeklyTsApiResponse.TimeSeries> entry : getWeeklyTsApiResponse
                                                        .getTimeSeries().entrySet()) {
                                                GetWeeklyTsResponse.TimeSeries data = new GetWeeklyTsResponse.TimeSeries();
                                                data.setOpen(entry.getValue().getOpen());
                                                data.setHigh(entry.getValue().getHigh());
                                                data.setLow(entry.getValue().getLow());
                                                data.setClose(entry.getValue().getClose());
                                                data.setAdjustedClose(entry.getValue().getAdjustedClose());
                                                data.setVolume(entry.getValue().getVolume());
                                                data.setDividendAmount(entry.getValue().getDividendAmount());
                                                timeSeries.put(entry.getKey(), data);
                                        }

                                        builder.timeSeries(timeSeries);

                                        return builder.build();
                                });
        };

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
                                .bodyToMono(GetMonthlyTsApiResponse.class)
                                .map((getMonthlyTsApiResponse) -> {
                                        GetMonthlyTsResponse.GetMonthlyTsResponseBuilder builder = GetMonthlyTsResponse
                                                        .builder();
                                        Map<String, GetMonthlyTsResponse.TimeSeries> timeSeries = new HashMap<>();
                                        for (Map.Entry<String, GetMonthlyTsApiResponse.TimeSeries> entry : getMonthlyTsApiResponse
                                                        .getTimeSeries().entrySet()) {
                                                GetMonthlyTsResponse.TimeSeries data = new GetMonthlyTsResponse.TimeSeries();
                                                data.setOpen(entry.getValue().getOpen());
                                                data.setHigh(entry.getValue().getHigh());
                                                data.setLow(entry.getValue().getLow());
                                                data.setClose(entry.getValue().getClose());
                                                data.setAdjustedClose(entry.getValue().getAdjustedClose());
                                                data.setVolume(entry.getValue().getVolume());
                                                data.setDividendAmount(entry.getValue().getDividendAmount());
                                                timeSeries.put(entry.getKey(), data);
                                        }

                                        builder.timeSeries(timeSeries);

                                        return builder.build();
                                });
        }
}
