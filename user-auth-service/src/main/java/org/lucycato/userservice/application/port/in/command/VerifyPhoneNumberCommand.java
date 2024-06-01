package org.lucycato.userservice.application.port.in.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.common.SelfValidating;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class VerifyPhoneNumberCommand extends SelfValidating<VerifyPhoneNumberCommand> {
    @NotNull
    private Long appUserId;

    @NotBlank
    private String phoneNumber;

    @NotBlank
    private String authorizedCode;

    public VerifyPhoneNumberCommand(Long appUserId, String phoneNumber, String authorizedCode) {
        this.appUserId = appUserId;
        this.phoneNumber = phoneNumber;
        this.authorizedCode = authorizedCode;

        this.validateSelf();
    }
}
