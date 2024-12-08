package com.sahil.stock.info.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sahil.stock.info.model.Stock;
import com.sahil.stock.info.dto.GlobalQuoteDto;


import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Service
@Transactional
@Slf4j
public class StockService {
    @Autowired
    private ApiService apiService;

    public Stock getGlobalQuote(String symbol) {
        Mono<GlobalQuoteDto> dtoMono = apiService.getGlobalQuote(symbol);
        if (dtoMono != null) {
            log.info("DTO fetched: " + dtoMono.toString());
            Stock stock = new Stock();
            dtoMono.map(dto -> {
                stock.setSymbol(dto.getSymbol());
                stock.setOpen(dto.getOpen());
                stock.setHigh(dto.getHigh());
                stock.setLow((dto.getLow()));
                stock.setPrice((dto.getPrice()));
                stock.setVolume((dto.getVolume()));
                stock.setPrevClose((dto.getPrevClose()));
                stock.setChange((dto.getChange()));
                stock.setChangePercent((dto.getChangePercent()));
                stock.setFetchedAt(new Date());
                return stock;
            });
        }
        return null;
    }
}
