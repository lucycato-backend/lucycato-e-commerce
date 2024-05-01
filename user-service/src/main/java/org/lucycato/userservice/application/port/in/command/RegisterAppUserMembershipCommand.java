package org.lucycato.userservice.application.port.in.command;


import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.common.SelfValidating;
import org.lucycato.userservice.domain.enums.MembershipGrade;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class RegisterAppUserMembershipCommand extends SelfValidating<RegisterAppUserMembershipCommand> {
    @NotNull
    private Long appUserId;

    @NotNull
    private MembershipGrade membershipGrade;

    public RegisterAppUserMembershipCommand(Long appUserId, MembershipGrade membershipGrade) {
        this.appUserId = appUserId;
        this.membershipGrade = membershipGrade;

        this.validateSelf();
    }
}
