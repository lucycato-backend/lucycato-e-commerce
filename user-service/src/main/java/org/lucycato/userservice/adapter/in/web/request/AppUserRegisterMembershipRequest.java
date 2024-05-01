package org.lucycato.userservice.adapter.in.web.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.userservice.domain.enums.MembershipGrade;

@Getter
@NoArgsConstructor
public class AppUserRegisterMembershipRequest {
    private MembershipGrade membershipGrade;
}
