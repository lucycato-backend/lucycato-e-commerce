package org.lucycato.userservice.application.port.out.result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.common.security.AdminUserRole;
import org.lucycato.userservice.model.info.DeviceInfo;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminUserResult {
    private Long adminUserId;

    private String nickName;

    private String name;

    private String email;

    private String password;

    private String phoneNumber;

    private String imageUrl;

    private List<AdminUserRole> adminUserRoles;

    private List<DeviceInfo> deviceInfos;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;
}
