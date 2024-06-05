package org.lucycato.boardcommandservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@EnableDiscoveryClient
@SpringBootApplication
public class BoardCommandServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BoardCommandServiceApplication.class, args);
    }
}
