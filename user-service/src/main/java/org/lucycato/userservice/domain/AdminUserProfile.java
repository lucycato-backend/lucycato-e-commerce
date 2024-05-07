package org.lucycato.userservice.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter(value = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AdminUserProfile {
    private String email;
    private LocalDateTime createdAt;
    private String imageUrl;

    public static AdminUserProfile create(String email, LocalDateTime createdAt, String imageUrl) {
        return AdminUserProfile.builder()
                .email(email)
                .createdAt(createdAt)
                .imageUrl(imageUrl)
                .build();
    }
}
