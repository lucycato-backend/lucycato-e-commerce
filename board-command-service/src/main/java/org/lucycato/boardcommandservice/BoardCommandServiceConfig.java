package org.lucycato.boardcommandservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"org.lucycato.common", "org.lucycato.mvc"})
public class BoardCommandServiceConfig {

    public static void main(String[] args) {
        SpringApplication.run(BoardCommandServiceConfig.class, args);
    }
}
