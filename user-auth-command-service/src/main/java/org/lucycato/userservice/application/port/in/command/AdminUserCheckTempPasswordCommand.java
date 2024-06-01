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
public class AdminUserCheckTempPasswordCommand extends SelfValidating<AdminUserCheckTempPasswordCommand> {

    @Email
    private String email;
    @NotBlank
    private String tempPassword;

    public AdminUserCheckTempPasswordCommand(String email, String tempPassword) {
        this.email = email;
        this.tempPassword = tempPassword;

        this.validateSelf();
    }
}
