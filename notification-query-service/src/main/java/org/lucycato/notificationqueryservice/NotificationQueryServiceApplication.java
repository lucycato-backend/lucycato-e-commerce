package org.lucycato.notificationqueryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class NotificationQueryServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(NotificationQueryServiceApplication.class, args);
    }

    //TODO: 전화번호 인증 발송 API 개발
}