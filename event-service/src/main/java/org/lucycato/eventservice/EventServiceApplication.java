package org.lucycato.eventservice;

import org.lucycato.common.api.Api;
import org.lucycato.common.error.ErrorCodeImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@SpringBootApplication
public class EventServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(EventServiceApplication.class, args);
    }

    @GetMapping
    public Mono<Api<String>> test() {
        if (true) {
            throw ErrorCodeImpl.NULL_POINT.build();
        }
        return Mono.just(Api.OK("HELLO!!"));
    }

}