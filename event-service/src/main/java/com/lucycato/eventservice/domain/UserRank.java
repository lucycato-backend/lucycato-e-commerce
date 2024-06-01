package com.lucycato.eventservice.domain;


import lombok.*;

@Getter
@Setter(value = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserRank {

    private String rank;

    public static UserRank from(String rank) {
        return UserRank.builder()
                .rank(rank)
                .build();
    }
}
