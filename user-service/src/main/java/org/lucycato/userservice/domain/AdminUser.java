package org.lucycato.userservice.domain;

import lombok.*;
import org.lucycato.common.security.AdminUserRole;
import org.lucycato.userservice.application.port.out.result.AdminUserResult;

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

    private final DeviceManagement currentDeviceManagement;

    private final LocalDateTime createdAt;

    private final LocalDateTime modifiedAt;

    public static AdminUser from(AdminUserResult result) {
        return AdminUser.builder()
                .adminUserId(result.getAdminUserId())
                .name(result.getName())
                .email(result.getEmail())
                .phoneNumber(result.getPhoneNumber())
                .imageUrl(result.getImageUrl())
                .roles(result.getAdminUserRoles())
                .currentDeviceManagement(null)
                .createdAt(result.getCreatedAt())
                .modifiedAt(result.getModifiedAt())
                .build();
    }
}
