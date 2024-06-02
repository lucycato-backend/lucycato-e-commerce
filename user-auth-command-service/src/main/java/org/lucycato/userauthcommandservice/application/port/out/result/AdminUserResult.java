package org.lucycato.userauthcommandservice.application.port.out.result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.common.security.AdminUserRole;
import org.lucycato.userauthcommandservice.adapter.out.persistence.entity.AdminUserJpaEntity;
import org.lucycato.userauthcommandservice.adapter.out.persistence.vo.DeviceVo;

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

    private List<DeviceVo> deviceVos;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

    public static AdminUserResult from(AdminUserJpaEntity entity) {
        return AdminUserResult.builder()
                .adminUserId(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .nickName(entity.getNickName())
                .phoneNumber(entity.getPhoneNumber())
                .imageUrl(entity.getImageUrl())
                .adminUserRoles(entity.getAdminUserRoles())
                .deviceVos(entity.getDeviceVos())
                .createdAt(entity.getCreatedAt())
                .modifiedAt(entity.getModifiedAt())
                .build();
    }
}
