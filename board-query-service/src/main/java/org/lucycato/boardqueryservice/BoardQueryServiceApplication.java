package org.lucycato.boardqueryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"org.lucycato.common","org.lucycato.webflux"})
@SpringBootApplication
public class BoardQueryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BoardQueryServiceApplication.class, args);
    }
}
