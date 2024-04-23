package org.lucycato.userservice.application.port.in.command;

import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.common.SelfValidating;
import org.lucycato.common.security.AdminUserRole;

import java.util.List;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class GetAppUserListByTeacherIdsCommand extends SelfValidating<GetAppUserListByTeacherIdsCommand> {
    @NotBlank
    private List<AdminUserRole> adminUserRoles;

    @NotBlank
    private List<Long> targetTeacherIds;

    public GetAppUserListByTeacherIdsCommand(List<AdminUserRole> adminUserRoles, List<Long> targetTeacherIds) {
        this.adminUserRoles = adminUserRoles;
        this.targetTeacherIds = targetTeacherIds;

        this.validateSelf();
    }
}
