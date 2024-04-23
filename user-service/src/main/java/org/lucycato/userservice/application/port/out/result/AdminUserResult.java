package org.lucycato.userservice.application.port.out.result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.common.security.AdminUserRole;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminUserResult {
    private Long adminUserId;

    private String name;

    private String email;

    private String nickName;

    private String password;

    private String phoneNumber;

    private String imageUrl;

    private List<AdminUserRole> adminUserRoles;

    private LocalDateTime lastLoginAt;

    private LocalDateTime lastLogoutAt;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

}
