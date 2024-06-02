package org.lucycato.userauthcommandservice.domain.enums;


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
