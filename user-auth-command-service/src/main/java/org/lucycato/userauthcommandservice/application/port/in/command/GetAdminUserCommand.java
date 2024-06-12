package org.lucycato.userauthcommandservice.application.port.in.command;

import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.common.SelfValidating;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class GetAdminUserCommand extends SelfValidating<GetAdminUserCommand>  {
    @NotNull
    private Long adminUserId;

    public GetAdminUserCommand(Long adminUserId) {
        this.adminUserId = adminUserId;

        this.validateSelf();
    }
}
