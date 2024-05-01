package org.lucycato.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

    //TODO: redis memory cashing

    //TODO: 학생, 신입, 경력 추가 정보 받기 API 개발 -> 석범님

    // TODO: 어드민 아이디 중복 check API 개발 -> 진영님

    // TODO: 아이디 찾기 API 개발 -> 민디님

    // TODO: 비밀번호 찾기 API 개발 -> 민우님

    // TODO: 마케팅 수신동의 API 개발 -> 경서님
}