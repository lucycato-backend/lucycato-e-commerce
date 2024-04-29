package org.lucycato.userservice.application.port.in.command;


import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.lucycato.common.SelfValidating;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class GetAppUserDeviceManagementCommand extends SelfValidating<GetAppUserDeviceManagementCommand> {
    @NotNull
    private Long appUserId;

    public GetAppUserDeviceManagementCommand(Long appUserId) {
        this.appUserId = appUserId;

        this.validateSelf();
    }
}
