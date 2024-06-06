package org.lucycato.eventqueryservice;

import com.google.common.eventbus.AllowConcurrentEvents;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.webflux.CommonWebClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@EnableDiscoveryClient
@RestController
@SpringBootApplication
public class EventQueryServiceApplication {

    private final CommonWebClient commonWebClient;

    public EventQueryServiceApplication(CommonWebClient commonWebClient) {
        this.commonWebClient = commonWebClient;
    }

    public static void main(String[] args) {
        SpringApplication.run(EventQueryServiceApplication.class, args);
    }

    @GetMapping("open-api/event-query/v1/test")
    public Mono<Test> hello() {
        String url = "lb://event-command-service/open-api/event-command/v1/test";
        return commonWebClient.sendGetRequestResultMono(url, Test.class);
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Test {
        private String name;

        private String des;
    }
}
