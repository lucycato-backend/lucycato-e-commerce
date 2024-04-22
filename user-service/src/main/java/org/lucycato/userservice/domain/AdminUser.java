package org.lucycato.userservice.domain;

import lombok.*;

@Getter
@Setter(value = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AdminUser {
    private final String name;

    private final String email;

    private final String phoneNumber;

    public static AdminUser create(
            String name,
            String email,
            String phoneNumber
    ) {
        return AdminUser.builder()
                .name(name)
                .email(email)
                .phoneNumber(phoneNumber)
                .build();
    }
}
