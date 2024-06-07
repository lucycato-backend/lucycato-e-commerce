package org.lucycato.eventcommandservice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.mvc.CommonRestTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableDiscoveryClient
@RestController
@SpringBootApplication
public class EventCommandServiceApplication {

    private final CommonRestTemplate commonRestTemplate;

    public EventCommandServiceApplication(CommonRestTemplate commonRestTemplate) {
        this.commonRestTemplate = commonRestTemplate;
    }

    public static void main(String[] args) {
        SpringApplication.run(EventCommandServiceApplication.class, args);
    }

    @GetMapping("open-api/v1/test2")
    public Test hello2() throws Exception {
        return new Test("fwe", "fewwfe");
    }

    @GetMapping("open-api/v1/test")
    public Test hello() throws Exception {
        String url = "lb://event-query-service/open-api/v1/test2";
        return commonRestTemplate.sendGetRequest(url, Test.class);
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Test {
        private String name;

        private String des;
    }
}
