package org.lucycato.userservice.application.port.in.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.common.SelfValidating;
import org.lucycato.userservice.domain.enums.PlatformType;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class GetAppUserCommand extends SelfValidating<GetAppUserCommand> {
    @NotNull
    private Long appUserId;

    @NotBlank
    private String currentAppUserDeviceMacAddress;

    @NotNull
    private PlatformType currentAppUserPlatformType;


    public GetAppUserCommand(Long appUserId, String currentAppUserDeviceMacAddress, PlatformType currentAppUserPlatformType) {
        this.appUserId = appUserId;
        this.currentAppUserDeviceMacAddress = currentAppUserDeviceMacAddress;
        this.currentAppUserPlatformType = currentAppUserPlatformType;

        this.validateSelf();
    }
}
