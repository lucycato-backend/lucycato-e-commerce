package org.lucycato.eventcommandservice;

import org.lucycato.boardcommandservice.BoardCommandServiceApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"org.lucycato.common","org.lucycato.mvc"})
@SpringBootApplication
public class EventCommandServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BoardCommandServiceApplication.class, args);
    }
}
