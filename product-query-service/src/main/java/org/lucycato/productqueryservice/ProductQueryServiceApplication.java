package org.lucycato.productqueryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@SpringBootApplication
public class ProductQueryServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProductQueryServiceApplication.class, args);
    }
}
