package com.br.saltoleto.webflux.micrometer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.blockhound.BlockHound;

@SpringBootApplication
public class WebFluxMicrometerApplication {
    static {
        BlockHound.install();
    }

    public static void main(String[] args) {
        SpringApplication.run(WebFluxMicrometerApplication.class, args);
    }

}
