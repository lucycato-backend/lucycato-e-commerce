package org.lucycato.gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableDiscoveryClient
@RestController
@SpringBootApplication
public class GatewayServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayServerApplication.class, args);
    }

    //TODO: eureka-server 구축

    //TODO: Passport -> user-service 그 외 서비스 공통 개발

    //TODO: JWT Context -> user-service 분리

    @GetMapping("health")
    public String health() {
        return "Health";
    }
}