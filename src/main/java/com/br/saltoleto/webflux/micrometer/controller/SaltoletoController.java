package com.br.saltoleto.webflux.micrometer.controller;

import com.br.saltoleto.webflux.micrometer.service.SaltoletoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/saltoleto")
@AllArgsConstructor
public class SaltoletoController {

    private final SaltoletoService saltoletoService;

    @GetMapping
    public Flux<String> getMultiplePets() {
        List<String> idList = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12");
        Flux<String> idFlux = Flux.fromIterable(idList);
        return idFlux.flatMap(id -> saltoletoService.getInfo(Long.valueOf(id)));
    }
}
