package org.lucycato.userservice.model.enums;


import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum SocialStatus {
    APPLE("apple"),
    GOOGLE("google"),
    NAVER("naver"),
    KAKAO("kakao"),
    ;
    private final String description;

}
