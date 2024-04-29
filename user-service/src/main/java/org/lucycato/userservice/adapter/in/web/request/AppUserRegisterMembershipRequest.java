package org.lucycato.userservice.adapter.in.web.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.userservice.model.enums.MembershipGrade;

@Getter
@NoArgsConstructor
public class AppUserRegisterMembershipRequest {
    private MembershipGrade membershipGrade;
}
