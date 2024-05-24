package org.lucycato.productcommandservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"org.lucycato.common", "org.lucycato.mvc"})
public class ProductCommandServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductCommandServiceApplication.class, args);
    }
}
