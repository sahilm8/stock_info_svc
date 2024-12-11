package com.sahil.stock.info.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.sahil.stock.info.model.Stock;
import com.sahil.stock.info.util.ApiFunctions;

import jakarta.annotation.PostConstruct;

import com.sahil.stock.info.dto.GlobalQuoteDto;


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
        webClient = WebClient.builder().baseUrl(apiUrl).build();
	}

    public Mono<Stock> getGlobalQuote(String symbol) {
        return webClient.get()
            .uri(uriBuilder -> uriBuilder
            .queryParam("apikey", apiKey)
            .queryParam("function", ApiFunctions.GLOBAL_QUOTE.getValue())
            .queryParam("datatype", "json")
            .queryParam("symbol", symbol)
            .build())                
            .retrieve()
            .bodyToMono(GlobalQuoteDto.class)
            .map(dto -> {
                Stock stock = new Stock();
                stock.setSymbol(dto.getSymbol());
                stock.setOpen(dto.getOpen());
                stock.setHigh(dto.getHigh());
                stock.setLow(dto.getLow());
                stock.setPrice(dto.getPrice());
                stock.setVolume(dto.getVolume());
                stock.setPrevClose(dto.getPrevClose());
                stock.setChange(dto.getChange());
                stock.setChangePercent(dto.getChangePercent());
                stock.setFetchedAt(new Date());
                return stock;
            });
    }
}
