package org.lucycato.userservice.domain;

import lombok.*;
import org.lucycato.userservice.adapter.out.persistence.entity.AdminUserJpaEntity;

import java.time.LocalDateTime;

@Getter
@Setter(value = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AdminUserProfile {
    private String email;
    private LocalDateTime createdAt;
    private String imageUrl;

    public static AdminUserProfile from(AdminUserJpaEntity entity) {
        return AdminUserProfile.builder()
                .email(entity.getEmail())
                .createdAt(entity.getCreatedAt())
                .imageUrl(entity.getImageUrl())
                .build();
    }
}
