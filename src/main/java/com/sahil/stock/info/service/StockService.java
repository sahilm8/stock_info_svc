package com.sahil.stock.info.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import com.sahil.stock.info.model.Stock;
import com.sahil.stock.info.model.StockTs;
import com.sahil.stock.info.model.StockTsAdjusted;
import com.sahil.stock.info.model.TimeSeries;
import com.sahil.stock.info.model.TimeSeriesAdjusted;
import com.sahil.stock.info.util.ApiFunctions;

import jakarta.annotation.PostConstruct;

import com.sahil.stock.info.dto.GlobalQuoteDto;
import com.sahil.stock.info.dto.TsDailyDto;
import com.sahil.stock.info.dto.TsIntraday15MinDto;
import com.sahil.stock.info.dto.TsIntraday1MinDto;
import com.sahil.stock.info.dto.TsIntraday30MinDto;
import com.sahil.stock.info.dto.TsIntraday5MinDto;
import com.sahil.stock.info.dto.TsIntraday60MinDto;
import com.sahil.stock.info.dto.TsIntradayDto;
import com.sahil.stock.info.dto.TsMonthlyDto;
import com.sahil.stock.info.dto.TsWeeklyDto;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class StockService {
    @Value("${stock.api_url}")
    private String apiUrl;

    @Value("${stock.api_key}")
    private String apiKey;
    
    private WebClient webClient;

    @PostConstruct
    public void init() {
        ExchangeStrategies strategies = ExchangeStrategies
        .builder()
        .codecs(configurer -> 
            configurer
            .defaultCodecs()
            .maxInMemorySize(1024 * 1024 * 10)  // 10MB
        )
        .build();

        webClient = WebClient
        .builder()
        .baseUrl(apiUrl)
        .exchangeStrategies(strategies)
        .build();
    }

    public Mono<Stock> getGlobalQuote(String symbol) {
        return webClient.get()
            .uri(uriBuilder -> uriBuilder
            .queryParam("function", ApiFunctions.GLOBAL_QUOTE.getValue())
            .queryParam("symbol", symbol)
            // datatype: json or csv
            .queryParam("datatype", "json")
            .queryParam("apikey", apiKey)
            .build())
            .retrieve()
            .bodyToMono(GlobalQuoteDto.class)
            .map(dto -> {
                log.info("dto: " + dto);
                Stock stock = new Stock();
                stock.setSymbol(dto.getSymbol());
                stock.setOpen(dto.getOpen());
                stock.setHigh(dto.getHigh());
                stock.setLow(dto.getLow());
                stock.setPrice(dto.getPrice());
                stock.setVolume(dto.getVolume());
                stock.setLatestTradingDay(dto.getLatestTradingDay());
                stock.setPrevClose(dto.getPrevClose());
                stock.setChange(dto.getChange());
                stock.setChangePercent(dto.getChangePercent());
                return stock;
            });
    }

    public Mono<TimeSeries> getTimeSeriesIntraday(String symbol, String interval) {
        return webClient.get()
            .uri(uriBuilder -> uriBuilder
            .queryParam("function", ApiFunctions.TIME_SERIES_INTRADAY.getValue())
            .queryParam("symbol", symbol)
            .queryParam("interval", interval)
            .queryParam("adjusted", false)
            .queryParam("extended_hours", true)
            // outputsize: compact (last 100 data points) or full (last 30 days data points)
            .queryParam("outputsize", "compact")
            // datatype: json or csv
            .queryParam("datatype", "json")
            .queryParam("apikey", apiKey)
            .build())
            .retrieve()
            .bodyToMono(getIntervalDtoClass(interval))
            .map(dto -> {
                log.info("dto: " + dto);
                TimeSeries timeSeries = new TimeSeries();
                Map<String, StockTs> ts = new HashMap<>();
                for (Map.Entry<String, Map<String, String>> entry : dto.getTimeSeries().entrySet()) {
                    StockTs stockTs = new StockTs();
                    stockTs.setOpen(new BigDecimal(dto.getOpen(entry.getKey())));
                    stockTs.setHigh(new BigDecimal(dto.getHigh(entry.getKey())));
                    stockTs.setLow(new BigDecimal(dto.getLow(entry.getKey())));
                    stockTs.setClose(new BigDecimal(dto.getClose(entry.getKey())));
                    stockTs.setVolume(new BigDecimal(dto.getVolume(entry.getKey())));
                    ts.put(entry.getKey(), stockTs);
                }
                timeSeries.setTimeSeries(ts);
                return timeSeries;
            });
    }

    public Mono<TimeSeries> getTimeSeriesDaily(String symbol) {
        return webClient.get()
            .uri(uriBuilder -> uriBuilder
            .queryParam("function", ApiFunctions.TIME_SERIES_DAILY.getValue())
            .queryParam("symbol", symbol)
            // outputsize: compact (last 100 data points) or full (last 20+ years data points)
            .queryParam("outputsize", "compact")
            // datatype: json or csv
            .queryParam("datatype", "json")
            .queryParam("apikey", apiKey)
            .build())
            .retrieve()
            .bodyToMono(TsDailyDto.class)
            .map(dto -> {
                log.info("dto: " + dto);
                TimeSeries timeSeries = new TimeSeries();
                Map<String, StockTs> ts = new HashMap<>();
                for (Map.Entry<String, Map<String, String>> entry : dto.getTimeSeries().entrySet()) {
                    StockTs stockTs = new StockTs();
                    stockTs.setOpen(new BigDecimal(dto.getOpen(entry.getKey())));
                    stockTs.setHigh(new BigDecimal(dto.getHigh(entry.getKey())));
                    stockTs.setLow(new BigDecimal(dto.getLow(entry.getKey())));
                    stockTs.setClose(new BigDecimal(dto.getClose(entry.getKey())));
                    stockTs.setVolume(new BigDecimal(dto.getVolume(entry.getKey())));
                    ts.put(entry.getKey(), stockTs);
                }
                timeSeries.setTimeSeries(ts);
                return timeSeries;
            });
    }

    public Mono<TimeSeriesAdjusted> getTimeSeriesWeekly(String symbol) {
        return webClient.get()
        .uri(uriBuilder -> uriBuilder
        .queryParam("function", ApiFunctions.TIME_SERIES_WEEKLY_ADJUSTED.getValue())
        .queryParam("symbol", symbol)
        // datatype: json or csv
        .queryParam("datatype", "json")
        .queryParam("apikey", apiKey)
        .build())
        .retrieve()
        .bodyToMono(TsWeeklyDto.class)
        .map(dto -> {
            log.info("dto: " + dto);
            TimeSeriesAdjusted timeSeriesAdjusted = new TimeSeriesAdjusted();
            Map<String, StockTsAdjusted> ts = new HashMap<>();
            for (Map.Entry<String, Map<String, String>> entry : dto.getTimeSeries().entrySet()) {
                StockTsAdjusted stockTsAdjusted = new StockTsAdjusted();
                stockTsAdjusted.setOpen(new BigDecimal(dto.getOpen(entry.getKey())));
                stockTsAdjusted.setHigh(new BigDecimal(dto.getHigh(entry.getKey())));
                stockTsAdjusted.setLow(new BigDecimal(dto.getLow(entry.getKey())));
                stockTsAdjusted.setClose(new BigDecimal(dto.getClose(entry.getKey())));
                stockTsAdjusted.setAdjustedClose(new BigDecimal(dto.getAdjustedClose(entry.getKey())));
                stockTsAdjusted.setVolume(new BigDecimal(dto.getVolume(entry.getKey())));
                stockTsAdjusted.setDividendAmount(new BigDecimal(dto.getDividendAmount(entry.getKey())));
                ts.put(entry.getKey(), stockTsAdjusted);
            }
            timeSeriesAdjusted.setTimeSeries(ts);
            return timeSeriesAdjusted;
        });
    }

    public Mono<TimeSeriesAdjusted> getTimeSeriesMonthly(String symbol) {
        return webClient.get()
        .uri(uriBuilder -> uriBuilder
        .queryParam("function", ApiFunctions.TIME_SERIES_MONTHLY_ADJUSTED.getValue())
        .queryParam("symbol", symbol)
        // datatype: json or csv
        .queryParam("datatype", "json")
        .queryParam("apikey", apiKey)
        .build())
        .retrieve()
        .bodyToMono(TsMonthlyDto.class)
        .map(dto -> {
            log.info("dto: " + dto);
            TimeSeriesAdjusted timeSeriesAdjusted = new TimeSeriesAdjusted();
            Map<String, StockTsAdjusted> ts = new HashMap<>();
            for (Map.Entry<String, Map<String, String>> entry : dto.getTimeSeries().entrySet()) {
                StockTsAdjusted stockTsAdjusted = new StockTsAdjusted();
                stockTsAdjusted.setOpen(new BigDecimal(dto.getOpen(entry.getKey())));
                stockTsAdjusted.setHigh(new BigDecimal(dto.getHigh(entry.getKey())));
                stockTsAdjusted.setLow(new BigDecimal(dto.getLow(entry.getKey())));
                stockTsAdjusted.setClose(new BigDecimal(dto.getClose(entry.getKey())));
                stockTsAdjusted.setAdjustedClose(new BigDecimal(dto.getAdjustedClose(entry.getKey())));
                stockTsAdjusted.setVolume(new BigDecimal(dto.getVolume(entry.getKey())));
                stockTsAdjusted.setDividendAmount(new BigDecimal(dto.getDividendAmount(entry.getKey())));
                ts.put(entry.getKey(), stockTsAdjusted);
            }
            timeSeriesAdjusted.setTimeSeries(ts);
            return timeSeriesAdjusted;
        });
    }

    private Class<? extends TsIntradayDto> getIntervalDtoClass(String interval) {
        switch (interval) {
            case "1min":
            return TsIntraday1MinDto.class;
            case "5min":
            return TsIntraday5MinDto.class;
            case "15min":
            return TsIntraday15MinDto.class;
            case "30min":
            return TsIntraday30MinDto.class;
            case "60min":
            return TsIntraday60MinDto.class;
            default:
            return TsIntradayDto.class;
        }
    }
}
