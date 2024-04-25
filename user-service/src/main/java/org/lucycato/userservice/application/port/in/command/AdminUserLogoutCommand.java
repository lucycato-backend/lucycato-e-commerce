package org.lucycato.userservice.application.port.in.command;

import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.common.SelfValidating;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class AdminUserLogoutCommand extends SelfValidating<AdminUserLogoutCommand> {
    @NotNull
    private Long adminMemberId;

    public AdminUserLogoutCommand(Long adminMemberId) {
        this.adminMemberId = adminMemberId;

        this.validateSelf();
    }
}
