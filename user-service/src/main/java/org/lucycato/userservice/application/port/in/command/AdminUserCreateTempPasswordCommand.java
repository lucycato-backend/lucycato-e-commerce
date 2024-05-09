package org.lucycato.userservice.application.port.in.command;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.common.SelfValidating;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class AdminUserCreateTempPasswordCommand extends SelfValidating<AdminUserCreateTempPasswordCommand> {
    @Email
    private String email;

    public AdminUserCreateTempPasswordCommand(String email) {
        this.email = email;

        this.validateSelf();
    }
}
