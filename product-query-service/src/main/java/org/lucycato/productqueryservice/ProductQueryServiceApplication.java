package org.lucycato.productqueryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ProductQueryServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProductQueryServiceApplication.class, args);
    }
}
