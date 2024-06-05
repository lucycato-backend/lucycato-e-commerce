package org.lucycato.boardqueryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@EnableDiscoveryClient
@SpringBootApplication
public class BoardQueryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BoardQueryServiceApplication.class, args);
    }
}
