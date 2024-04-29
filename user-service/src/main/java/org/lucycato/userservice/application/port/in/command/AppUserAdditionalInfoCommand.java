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
public class AppUserAdditionalInfoCommand extends SelfValidating<AppUserAdditionalInfoCommand> {
    @NotNull
    private Long appUserId;

    @NotBlank
    private String name;

    @NotBlank
    private String nickName;

    @NotBlank
    private String email;

    @NotBlank
    private String phoneNumber;

    public AppUserAdditionalInfoCommand(Long appUserId, String name, String nickName, String email, String phoneNumber) {
        this.appUserId = appUserId;
        this.name = name;
        this.nickName = nickName;
        this.email = email;
        this.phoneNumber = phoneNumber;

        this.validateSelf();
    }
}
