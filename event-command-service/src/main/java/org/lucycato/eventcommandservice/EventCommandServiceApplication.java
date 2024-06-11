package org.lucycato.eventcommandservice;

import org.lucycato.common.annotation.resolver.AdminUserHeaders;
import org.lucycato.common.annotation.resolver.AppUserHeaders;
import org.lucycato.common.resolver.AdminUserHeaderDetail;
import org.lucycato.common.resolver.AppUserHeaderDetail;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@EnableDiscoveryClient
@RestController
@SpringBootApplication
public class EventCommandServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EventCommandServiceApplication.class, args);
    }

    @GetMapping("open-api/v1/test")
    public Mono<String> test() {
        return Mono.just("HELLO");
    }

    @GetMapping("api/app/v1/test")
    public Mono<String> test2(@AppUserHeaders AppUserHeaderDetail appUserHeaderDetail) {
        return Mono.just("HELLO" + appUserHeaderDetail.getAppUserId());
    }

    @GetMapping("api/admin/v1/test")
    public Mono<String> test2(@AdminUserHeaders AdminUserHeaderDetail adminUserHeaderDetail) {
        return Mono.just("HELLO" + adminUserHeaderDetail.getAdminUserId());
    }
}
