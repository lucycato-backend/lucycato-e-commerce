package org.lucycato.userauthcommandservice.adapter.in.web.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.userauthcommandservice.domain.enums.MembershipGrade;

@Getter
@NoArgsConstructor
public class AppUserRegisterMembershipRequest {
    private MembershipGrade membershipGrade;
}
