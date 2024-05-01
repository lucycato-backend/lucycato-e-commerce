package org.lucycato.userservice.domain;

import lombok.*;
import org.lucycato.userservice.adapter.out.persistence.vo.DeviceVo;
import org.lucycato.userservice.adapter.out.persistence.vo.PlatformVo;
import org.lucycato.userservice.application.port.out.result.AppUserMembershipResult;
import org.lucycato.userservice.application.port.out.result.AppUserResult;
import org.lucycato.userservice.domain.enums.AppUserBadge;
import org.lucycato.userservice.domain.enums.AppUserGrade;
import org.lucycato.userservice.domain.enums.PlatformType;
import org.lucycato.userservice.domain.enums.SocialStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@Setter(value = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AppUser {
    private final Long appUserId;

    private final SocialStatus socialStatus;

    private final String name;

    private final String email;

    private final String phoneNumber;

    private final String imageUrl;

    private final AppUserGrade grade;

    private final List<AppUserBadge> badges;

    private final DeviceManagement currentDeviceManagement;

    private List<AppUserMembership> appUserMemberships;

    private final LocalDateTime createdAt;

    private final LocalDateTime modifiedAt;

    public static AppUser from(AppUserResult result) {
        List<AppUserMembership> appUserMemberships = result.getMembershipResults()
                .stream()
                .map(AppUserMembership::from)
                .toList();

        return AppUser.builder()
                .appUserId(result.getAppUserId())
                .socialStatus(result.getSocialStatus())
                .name(result.getName())
                .email(result.getEmail())
                .phoneNumber(result.getPhoneNumber())
                .imageUrl(result.getImageUrl())
                .grade(result.getGrade())
                .badges(result.getBadges())
                .currentDeviceManagement(null)
                .appUserMemberships(appUserMemberships)
                .createdAt(result.getCreatedAt())
                .modifiedAt(result.getModifiedAt())
                .build();
    }

    public static AppUser from(AppUserResult result, String currentAppUserDeviceMacAddress, PlatformType currentAppUserPlatformType) {
        List<DeviceVo> currentDeviceVos = result.getDeviceVos()
                .stream()
                .filter(deviceVo -> deviceVo.getDeviceManAddress().equals(currentAppUserDeviceMacAddress))
                .toList();
        DeviceManagement currentDeviceManager = null;
        if (!currentDeviceVos.isEmpty()) {
            List<Platform> currentPlatform = currentDeviceVos.get(0).getPlatformVos()
                    .stream()
                    .filter(platformVo -> platformVo.getPlatformType().equals(currentAppUserPlatformType))
                    .map(platformVo -> Platform.create(
                            platformVo.getPlatformType(),
                            platformVo.getPlatformVersion(),
                            platformVo.getNetworkType(),
                            platformVo.getPlatformVersion(),
                            platformVo.getLastLoginAt(),
                            platformVo.getLastLogoutAt()
                    ))
                    .toList();

            DeviceVo currentDeviceVo = currentDeviceVos.get(0);

            currentDeviceManager = DeviceManagement.create(
                    currentDeviceVo.getDeviceManAddress(),
                    currentDeviceVo.getDeviceFcmToken(),
                    currentDeviceVo.getDeviceOsType(),
                    currentDeviceVo.getDeviceOsVersion(),
                    currentPlatform
            );
        }

        List<AppUserMembership> appUserMemberships = result.getMembershipResults()
                .stream()
                .map(AppUserMembership::from)
                .toList();

        return AppUser.builder()
                .appUserId(result.getAppUserId())
                .socialStatus(result.getSocialStatus())
                .name(result.getName())
                .email(result.getEmail())
                .phoneNumber(result.getPhoneNumber())
                .imageUrl(result.getImageUrl())
                .grade(result.getGrade())
                .badges(result.getBadges())
                .currentDeviceManagement(currentDeviceManager)
                .appUserMemberships(appUserMemberships)
                .createdAt(result.getCreatedAt())
                .modifiedAt(result.getModifiedAt())
                .build();
    }
}
