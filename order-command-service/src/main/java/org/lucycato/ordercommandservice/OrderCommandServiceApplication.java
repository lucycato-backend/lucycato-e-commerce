package org.lucycato.ordercommandservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class OrderCommandServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderCommandServiceApplication.class, args);
    }

}
