package org.lucycato.userauthcommandservice.domain;

import lombok.*;
import org.lucycato.userauthcommandservice.application.port.out.result.AppUserResult;
import org.lucycato.userauthcommandservice.domain.enums.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter(value = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AppUser {
    private final Long appUserId;

    private final AppUserStatus status;

    private final SocialStatus socialStatus;

    private final String name;

    private final String email;

    private final String phoneNumber;

    private final String imageUrl;

    private final AppUserGrade grade;

    private final List<AppUserBadge> badges;

    private List<AppUserMembership> appUserMemberships;

    private final LocalDateTime createdAt;

    public static AppUser from(AppUserResult result) {
        List<AppUserMembership> appUserMemberships = result.getMembershipResults()
                .stream()
                .map(AppUserMembership::from)
                .toList();

        return AppUser.builder()
                .appUserId(result.getAppUserId())
                .status(result.getStatus())
                .socialStatus(result.getSocialStatus())
                .name(result.getName())
                .email(result.getEmail())
                .phoneNumber(result.getPhoneNumber())
                .imageUrl(result.getImageUrl())
                .grade(result.getGrade())
                .badges(result.getBadges())
                .appUserMemberships(appUserMemberships)
                .createdAt(result.getCreatedAt())
                .build();
    }
}
