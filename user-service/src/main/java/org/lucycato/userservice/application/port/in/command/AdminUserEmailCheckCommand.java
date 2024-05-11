package org.lucycato.userservice.application.port.in.command;

import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.common.SelfValidating;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class AdminUserEmailCheckCommand extends SelfValidating<AdminUserEmailCheckCommand> {

    @NotBlank
    private String email;

    public AdminUserEmailCheckCommand(String email) {
        this.email = email;

        this.validateSelf();
    }
}
