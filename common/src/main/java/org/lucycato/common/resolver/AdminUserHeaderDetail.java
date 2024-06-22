package org.lucycato.common.resolver;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.lucycato.common.SelfValidating;
import org.lucycato.common.security.AdminUserRole;

import java.util.List;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class AdminUserHeaderDetail extends SelfValidating<AdminUserHeaderDetail> {
    @NotNull
    private Long adminUserId;

    @NotBlank
    private List<AdminUserRole> adminUserRoles;

    @NotBlank
    private String currentAppUserDeviceMacAddress;

    @NotBlank
    private String currentAppUserPlatFormType;

    public AdminUserHeaderDetail(Long adminUserId, List<AdminUserRole> adminUserRoles, String currentAppUserDeviceMacAddress, String currentAppUserPlatFormType) {
        this.adminUserId = adminUserId;
        this.adminUserRoles = adminUserRoles;
        this.currentAppUserDeviceMacAddress = currentAppUserDeviceMacAddress;
        this.currentAppUserPlatFormType = currentAppUserPlatFormType;
    }
}
