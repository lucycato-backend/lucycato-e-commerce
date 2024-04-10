package org.lucycato.userservice.application.port.in.command;


import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.common.SelfValidating;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class AppLogoutCommand extends SelfValidating<AppLogoutCommand> {
    @NotNull
    private Long userId;

    public AppLogoutCommand(Long userId) {
        this.userId = userId;

        this.validateSelf();
    }
}
