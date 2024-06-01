package org.lucycato.notificationcommandservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"org.lucycato.common","org.lucycato.mvc"})
@SpringBootApplication
public class NotificationCommandServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotificationCommandServiceApplication.class, args);
    }
}
