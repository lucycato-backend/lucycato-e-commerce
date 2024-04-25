package org.lucycato.authserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AuthServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthServerApplication.class, args);
    }

    //TODO: 권한 인증 요청 API 개발
    //TODO: 권한 인증 수락 API 개발
}