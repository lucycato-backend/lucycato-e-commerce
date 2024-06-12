package org.lucycato.notificationqueryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"org.lucycato.common", "org.lucycato.webflux"})
public class NotificationQueryServiceConfig {
    public static void main(String[] args) {
        SpringApplication.run(NotificationQueryServiceConfig.class, args);
    }

    //TODO: 전화번호 인증 발송 API 개발
}