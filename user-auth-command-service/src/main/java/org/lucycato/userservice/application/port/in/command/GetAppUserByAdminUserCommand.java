package org.lucycato.userservice.application.port.in.command;


import jakarta.validation.constraints.NotEmpty;
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
public class GetAppUserByAdminUserCommand extends SelfValidating<GetAppUserByAdminUserCommand> {
    @NotEmpty
    private List<AdminUserRole> adminUserRoles;

    @NotNull
    private Long targetAppUserId;

    public GetAppUserByAdminUserCommand(List<AdminUserRole> adminUserRoles, Long targetAppUserId) {
        this.adminUserRoles = adminUserRoles;
        this.targetAppUserId = targetAppUserId;

        this.validateSelf();

    }
}
