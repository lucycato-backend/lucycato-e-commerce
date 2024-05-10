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
public class AppUserAdditionalCareerCommand extends SelfValidating<AppUserAdditionalCareerCommand> {

    @NotNull
    private Long appUserId;

    @NotBlank
    private String career;

    @NotBlank
    private String careerDetail;

    public AppUserAdditionalCareerCommand(Long appUserId, String career, String careerDetail) {
        this.appUserId = appUserId;
        this.career = career;
        this.careerDetail = careerDetail;

        this.validateSelf();
    }
}
