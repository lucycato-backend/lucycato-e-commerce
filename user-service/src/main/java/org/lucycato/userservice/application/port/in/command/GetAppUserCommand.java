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
public class GetAppUserCommand extends SelfValidating<GetAppUserCommand> {
    @NotBlank
    private List<AdminUserRole> adminUserRoles;

    @NotNull
    private Long targetAdminAppUserId;

    public GetAppUserCommand(List<AdminUserRole> adminUserRoles, Long targetAdminAppUserId) {
        this.adminUserRoles = adminUserRoles;
        this.targetAdminAppUserId = targetAdminAppUserId;

        this.validateSelf();

    }
}
