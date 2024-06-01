package org.lucycato.userservice.application.port.in.command;

import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.common.SelfValidating;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class GetAdminUserDeviceInfoCommand extends SelfValidating<GetAdminUserDeviceInfoCommand> {
    @NotNull
    private Long adminUserId;

    public GetAdminUserDeviceInfoCommand(Long adminUserId) {
        this.adminUserId = adminUserId;

        this.validateSelf();
    }
}
