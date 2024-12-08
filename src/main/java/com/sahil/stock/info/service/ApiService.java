package com.sahil.stock.info.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.sahil.stock.info.dto.GlobalQuoteDto;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class ApiService {
    private final WebClient webClient;
    
    public ApiService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<GlobalQuoteDto> fetchGlobalQuote(String endpoint) {
        return webClient.get()
                        .uri(endpoint)
                        .retrieve()
                        .bodyToMono(GlobalQuoteDto.class)
                        .doOnError(e -> log.error("Error occurred: " + e.getMessage()))
                        .onErrorReturn(null);
    }
}
