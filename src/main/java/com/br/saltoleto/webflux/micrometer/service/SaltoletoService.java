package com.br.saltoleto.webflux.micrometer.service;

import io.github.resilience4j.ratelimiter.RateLimiterRegistry;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@Slf4j
public class SaltoletoService {
    private final WebClient webClient;
    private final Timer apiRequestTimer;

    public SaltoletoService(WebClient.Builder webClientBuilder, MeterRegistry meterRegistry) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8080/infos").build();
        this.apiRequestTimer = Timer.builder("saltoleto.api.requests.duration")
                .register(meterRegistry);
    }

    @RateLimiter(name = "SaltoletoRateLimiter")
    public Mono<String> getInfo(Long id) {
        return Mono.defer(() -> {
            Timer.Sample sample = Timer.start();
            return webClient.get()
                    .uri("/{id}", id)
                    .retrieve()
                    .bodyToMono(String.class)
                    .subscribeOn(Schedulers.boundedElastic())
                    .doOnSubscribe(s -> log.info("API call scheduled to: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + " ID -> " + id))
                    .doOnTerminate(() -> {
                        sample.stop(apiRequestTimer);
                    });
        });
    }
}