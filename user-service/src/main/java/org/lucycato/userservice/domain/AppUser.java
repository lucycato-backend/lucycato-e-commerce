package org.lucycato.userservice.domain;

import lombok.*;
import org.lucycato.userservice.model.enums.AppUserBadge;
import org.lucycato.userservice.model.enums.AppUserGrade;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter(value = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AppUser {
    private final Long appUserId;

    private final String nickName;

    private final String name;

    private final String email;

    private final String phoneNumber;

    private final String imageUrl;

    private final AppUserGrade grade;

    private final List<AppUserBadge> badges;

    private final LocalDateTime lastLoginAt;

    private final LocalDateTime lastLogoutAt;

    private final LocalDateTime createdAt;

    private final LocalDateTime modifiedAt;

    public static AppUser created(
            Long appUserId,
            String nickName,
            String name,
            String email,
            String phoneNumber,
            String imageUrl,
            AppUserGrade grade,
            List<AppUserBadge> badges,
            LocalDateTime lastLoginAt,
            LocalDateTime lastLogoutAt,
            LocalDateTime createdAt,
            LocalDateTime modifiedAt
    ) {
        return AppUser.builder()
                .appUserId(appUserId)
                .nickName(nickName)
                .name(name)
                .email(email)
                .phoneNumber(phoneNumber)
                .imageUrl(imageUrl)
                .grade(grade)
                .badges(badges)
                .lastLoginAt(lastLoginAt)
                .lastLogoutAt(lastLogoutAt)
                .createdAt(createdAt)
                .modifiedAt(modifiedAt)
                .build();
    }
}
