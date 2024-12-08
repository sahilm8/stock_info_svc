package com.sahil.stock.info.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.sahil.stock.info.dto.GlobalQuoteDto;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class ApiService {
    @Value("${stock.api_key}")
    private String apiKey;    
    private WebClient webClient = WebClient.builder().baseUrl(apiKey).build();

    public Mono<GlobalQuoteDto> getGlobalQuote(String endpoint) {
        return webClient.get()
                        .uri(endpoint)
                        .retrieve()
                        .bodyToMono(GlobalQuoteDto.class)
                        .doOnError(e -> log.error("Error occurred: " + e.getMessage()))
                        .onErrorReturn(null);
    }
}
