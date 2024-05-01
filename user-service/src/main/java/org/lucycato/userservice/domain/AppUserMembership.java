package org.lucycato.userservice.domain;

import lombok.*;
import org.lucycato.userservice.application.port.out.result.AppUserMembershipResult;
import org.lucycato.userservice.domain.enums.MembershipGrade;

import java.time.LocalDateTime;

@Getter
@Setter(value = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AppUserMembership {
    private Long appUserMembershipId;

    private Long appUserId;

    private MembershipGrade membershipGrade;

    private Boolean isExpired;

    private LocalDateTime createdAt;

    private LocalDateTime expiredAt;

    public static AppUserMembership from(AppUserMembershipResult result) {
        return AppUserMembership.builder()
                .appUserMembershipId(result.getAppUserMembershipId())
                .appUserId(result.getAppUserId())
                .membershipGrade(result.getMembershipGrade())
                .isExpired(result.getIsExpired())
                .createdAt(result.getCreatedAt())
                .expiredAt(result.getExpiredAt())
                .build();
    }
}
