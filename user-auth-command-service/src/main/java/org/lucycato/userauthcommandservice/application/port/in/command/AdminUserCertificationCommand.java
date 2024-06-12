package org.lucycato.userauthcommandservice.application.port.in.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.common.SelfValidating;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class AdminUserCertificationCommand extends SelfValidating<AdminUserCertificationCommand> {
    @NotNull
    private String name;

    @NotBlank
    private String phoneNumber;

    public AdminUserCertificationCommand(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;

        this.validateSelf();
    }
}
