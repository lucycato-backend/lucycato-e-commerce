package org.lucycato.userauthqueryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class UserAuthQueryServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserAuthQueryServiceApplication.class, args);
    }
}