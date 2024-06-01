package org.lucycato.ordercommandservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"org.lucycato.common", "org.lucycato.mvc"})
public class OrderCommandServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderCommandServiceApplication.class, args);
    }

}
