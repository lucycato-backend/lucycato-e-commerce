package org.lucycato.eventqueryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"org.lucycato.common","org.lucycato.webflux"})
@SpringBootApplication
public class EventQueryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EventQueryServiceApplication.class, args);
    }
}
