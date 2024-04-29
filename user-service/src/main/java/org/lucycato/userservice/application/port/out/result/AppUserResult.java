package org.lucycato.userservice.application.port.out.result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.userservice.model.enums.AppUserBadge;
import org.lucycato.userservice.model.enums.AppUserGrade;
import org.lucycato.userservice.model.enums.SocialStatus;
import org.lucycato.userservice.model.info.DeviceInfo;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppUserResult {
    private Long appUserId;

    private SocialStatus socialStatus;

    private String nickName;

    private String name;

    private String email;

    private String password;

    private String phoneNumber;

    private String imageUrl;

    private AppUserGrade grade;

    private List<AppUserBadge> badges;

    private List<DeviceInfo> deviceInfos;

    private LocalDateTime lastLoginAt;

    private LocalDateTime lastLogoutAt;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;
}
