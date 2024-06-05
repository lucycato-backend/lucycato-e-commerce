package org.lucycato.userauthcommandservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class UserAuthCommandServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserAuthCommandServiceApplication.class, args);
    }
}