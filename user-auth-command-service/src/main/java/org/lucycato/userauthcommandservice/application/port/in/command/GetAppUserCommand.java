package org.lucycato.userauthcommandservice.application.port.in.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.common.SelfValidating;
import org.lucycato.userauthcommandservice.domain.enums.PlatformType;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class GetAppUserCommand extends SelfValidating<GetAppUserCommand> {
    @NotNull
    private Long appUserId;


    public GetAppUserCommand(Long appUserId) {
        this.appUserId = appUserId;

        this.validateSelf();
    }
}
