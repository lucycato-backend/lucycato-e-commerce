package org.lucycato.notificationcommandservice;

import org.lucycato.boardcommandservice.BoardCommandServiceApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"org.lucycato.common","org.lucycato.mvc"})
public class NotificationCommandServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BoardCommandServiceApplication.class, args);
    }
}
