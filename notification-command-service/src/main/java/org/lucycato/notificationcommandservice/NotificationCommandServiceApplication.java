package org.lucycato.notificationcommandservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class NotificationCommandServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotificationCommandServiceApplication.class, args);
    }
}
