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
public class GetAppUserListByLectureIdsCommand extends SelfValidating<GetAppUserListByLectureIdsCommand> {
    @NotEmpty
    private List<AdminUserRole> adminUserRoles;

    @NotEmpty
    private List<Long> targetLectureIds;

    public GetAppUserListByLectureIdsCommand(List<AdminUserRole> adminUserRoles, List<Long> targetLectureIds) {
        this.adminUserRoles = adminUserRoles;
        this.targetLectureIds = targetLectureIds;

        this.validateSelf();
    }
}
