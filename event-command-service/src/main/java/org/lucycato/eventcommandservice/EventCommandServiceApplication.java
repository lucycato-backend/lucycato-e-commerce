package org.lucycato.eventcommandservice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableDiscoveryClient
@RestController
@SpringBootApplication
public class EventCommandServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EventCommandServiceApplication.class, args);
    }

    @GetMapping("open-api/event-command/v1/test")
    public Test hello() {
        return new Test("Hello", "Good");
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Test {
        private String name;

        private String des;
    }
}
