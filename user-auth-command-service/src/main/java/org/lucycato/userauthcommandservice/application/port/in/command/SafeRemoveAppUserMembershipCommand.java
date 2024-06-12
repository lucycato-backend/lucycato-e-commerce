package org.lucycato.userauthcommandservice.application.port.in.command;


import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.common.SelfValidating;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class SafeRemoveAppUserMembershipCommand extends SelfValidating<SafeRemoveAppUserMembershipCommand> {
    @NotNull
    private Long appUserId;

    @NotNull
    private Long appUserMembershipId;

    public SafeRemoveAppUserMembershipCommand(Long appUserId, Long appUserMembershipId) {
        this.appUserId = appUserId;
        this.appUserMembershipId = appUserMembershipId;

        this.validateSelf();
    }
}
