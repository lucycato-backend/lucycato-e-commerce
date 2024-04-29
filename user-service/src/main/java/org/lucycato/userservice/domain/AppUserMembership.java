package org.lucycato.userservice.domain;

import lombok.*;
import org.lucycato.userservice.model.enums.MembershipGrade;

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

    public static AppUserMembership create(
            Long appUserMembershipId,
            Long appUserId,
            MembershipGrade membershipGrade,
            Boolean isExpired,
            LocalDateTime createdAt,
            LocalDateTime expiredAt
    ) {
        return AppUserMembership.builder()
                .appUserMembershipId(appUserMembershipId)
                .appUserId(appUserId)
                .membershipGrade(membershipGrade)
                .isExpired(isExpired)
                .createdAt(createdAt)
                .expiredAt(expiredAt)
                .build();
    }
}
