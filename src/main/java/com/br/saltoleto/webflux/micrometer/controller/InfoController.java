package com.br.saltoleto.webflux.micrometer.controller;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/infos")
@Slf4j
public class InfoController {
    static Map<String, String> data = new HashMap<>();

    static {
        data.put("1", "{\"userId\":1,\"id\":5,\"title\":\"laboriosammollitiaetenimquasiadipisciquiaprovidentillum\",\"completed\":false}");
        data.put("2", "{\"userId\":1,\"id\":5,\"title\":\"laboriosammollitiaetenimquasiadipisciquiaprovidentillum\",\"completed\":false}");
        data.put("3", "{\"userId\":1,\"id\":5,\"title\":\"laboriosammollitiaetenimquasiadipisciquiaprovidentillum\",\"completed\":false}");
        data.put("4", "{\"userId\":1,\"id\":5,\"title\":\"laboriosammollitiaetenimquasiadipisciquiaprovidentillum\",\"completed\":false}");
        data.put("5", "{\"userId\":1,\"id\":5,\"title\":\"laboriosammollitiaetenimquasiadipisciquiaprovidentillum\",\"completed\":false}");
        data.put("6", "{\"userId\":1,\"id\":5,\"title\":\"laboriosammollitiaetenimquasiadipisciquiaprovidentillum\",\"completed\":false}");
        data.put("7", "{\"userId\":1,\"id\":5,\"title\":\"laboriosammollitiaetenimquasiadipisciquiaprovidentillum\",\"completed\":false}");
        data.put("8", "{\"userId\":1,\"id\":5,\"title\":\"laboriosammollitiaetenimquasiadipisciquiaprovidentillum\",\"completed\":false}");
        data.put("9", "{\"userId\":1,\"id\":5,\"title\":\"laboriosammollitiaetenimquasiadipisciquiaprovidentillum\",\"completed\":false}");
        data.put("10", "{\"userId\":1,\"id\":5,\"title\":\"laboriosammollitiaetenimquasiadipisciquiaprovidentillum\",\"completed\":false}");
        data.put("11", "{\"userId\":1,\"id\":5,\"title\":\"laboriosammollitiaetenimquasiadipisciquiaprovidentillum\",\"completed\":false}");
        data.put("12", "{\"userId\":1,\"id\":5,\"title\":\"laboriosammollitiaetenimquasiadipisciquiaprovidentillum\",\"completed\":false}");
    }

    @GetMapping("/{id}")
    public Mono<String> getInfoById(@PathVariable String id) {
        log.info("API call completed for ID: " + id + " at: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        return Mono.just(data.get(id));
    }

}
