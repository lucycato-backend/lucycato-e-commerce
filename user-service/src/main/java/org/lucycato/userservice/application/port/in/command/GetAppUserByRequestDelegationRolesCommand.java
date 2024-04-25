package org.lucycato.userservice.application.port.in.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.common.SelfValidating;
import org.lucycato.common.security.AdminUserRole;

import java.util.List;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class GetAppUserByRequestDelegationRolesCommand extends SelfValidating<GetAppUserByRequestDelegationRolesCommand> {
    @NotEmpty
    private List<AdminUserRole> adminUserRoles;

    @NotEmpty
    private List<AdminUserRole> requestDelegationRoles;

    public GetAppUserByRequestDelegationRolesCommand(List<AdminUserRole> adminUserRoles, List<AdminUserRole> requestDelegationRoles) {
        this.adminUserRoles = adminUserRoles;
        this.requestDelegationRoles = requestDelegationRoles;

        this.validateSelf();
    }
}
