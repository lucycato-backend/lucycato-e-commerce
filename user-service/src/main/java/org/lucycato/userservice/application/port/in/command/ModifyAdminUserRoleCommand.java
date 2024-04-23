package org.lucycato.userservice.application.port.in.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.common.SelfValidating;
import org.lucycato.common.security.AdminUserRole;

import java.util.List;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ModifyAdminUserRoleCommand extends SelfValidating<ModifyAdminUserRoleCommand> {
    @NotBlank
    private List<AdminUserRole> adminUserRoles;

    @NotNull
    private Long targetAdminUserId;

    @NotNull
    private AdminUserRole targetChangeRole;

    public ModifyAdminUserRoleCommand(List<AdminUserRole> adminUserRoles, Long targetAdminUserId, AdminUserRole targetChangeRole) {
        this.adminUserRoles = adminUserRoles;
        this.targetAdminUserId = targetAdminUserId;
        this.targetChangeRole = targetChangeRole;

        this.validateSelf();
    }
}
