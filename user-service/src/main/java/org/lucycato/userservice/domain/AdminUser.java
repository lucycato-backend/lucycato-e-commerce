package org.lucycato.userservice.domain;

import lombok.*;
import org.lucycato.common.security.AdminUserRole;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter(value = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AdminUser {
    private final Long adminUserId;

    private final String name;

    private final String email;

    private final String phoneNumber;

    private final String imageUrl;

    private final List<AdminUserRole> roles;

    private final LocalDateTime lastLoginAt;

    private final LocalDateTime lastLogoutAt;

    private final LocalDateTime createdAt;

    private final LocalDateTime modifiedAt;

    public static AdminUser create(
            Long adminUserId,
            String name,
            String email,
            String phoneNumber,
            String imageUrl,
            List<AdminUserRole> roles,
            LocalDateTime lastLoginAt,
            LocalDateTime lastLogoutAt,
            LocalDateTime createdAt,
            LocalDateTime modifiedAt
    ) {
        return AdminUser.builder()
                .adminUserId(adminUserId)
                .name(name)
                .email(email)
                .phoneNumber(phoneNumber)
                .imageUrl(imageUrl)
                .roles(roles)
                .lastLoginAt(lastLoginAt)
                .lastLogoutAt(lastLogoutAt)
                .createdAt(createdAt)
                .modifiedAt(modifiedAt)
                .build();
    }
}
