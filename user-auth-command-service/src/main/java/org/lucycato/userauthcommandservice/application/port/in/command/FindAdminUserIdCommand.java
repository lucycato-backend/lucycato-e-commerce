package org.lucycato.userauthcommandservice.application.port.in.command;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.common.SelfValidating;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class FindAdminUserIdCommand extends SelfValidating<FindAdminUserIdCommand> {
    @NotBlank
    private String name;
    @NotBlank
    private String phone;
    @NotNull
    @Pattern(regexp = "\\d{6}")
    private String phoneNumberAuthCode;

    public FindAdminUserIdCommand(String name, String phone, String phoneNumberAuthCode) {
        this.name = name;
        this.phone = phone;
        this.phoneNumberAuthCode = phoneNumberAuthCode;

        this.validateSelf();
    }
}
