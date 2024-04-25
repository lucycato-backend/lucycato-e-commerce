package org.lucycato.userservice.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import org.lucycato.common.annotation.hexagonal.out.PersistenceAdapter;
import org.lucycato.common.error.ErrorCodeImpl;
import org.lucycato.common.exception.ApiExceptionImpl;
import org.lucycato.common.security.AdminUserRole;
import org.lucycato.userservice.adapter.out.persistence.entity.AdminUserJpaEntity;
import org.lucycato.userservice.application.port.out.AdminUserPort;
import org.lucycato.userservice.application.port.out.result.AdminUserResult;
import org.lucycato.userservice.model.enums.AppOrBrowserType;
import org.lucycato.userservice.model.enums.DeviceOsType;
import org.lucycato.userservice.model.enums.NetworkType;
import org.lucycato.userservice.model.info.AppOrBrowserInfo;
import org.lucycato.userservice.model.info.DeviceInfo;

import java.time.LocalDateTime;
import java.util.*;

@PersistenceAdapter
@RequiredArgsConstructor
public class AdminUserPersistenceAdapter implements AdminUserPort {

    private final AdminUserJpaRepository adminUserJpaRepository;

    @Override
    public AdminUserResult registerAdminUser(
            String nickName,
            String name,
            String email,
            String password,
            String phoneNumber,
            String deviceMacAddress,
            String deviceFcmToken,
            DeviceOsType deviceOsType,
            String deviceOsVersion,
            AppOrBrowserType appOrBrowserType,
            String appOrBrowserVersion,
            NetworkType networkType,
            String locale

    ) {
        AppOrBrowserInfo appOrBrowserInfo = new AppOrBrowserInfo(
                appOrBrowserType,
                appOrBrowserVersion,
                networkType,
                locale,
                LocalDateTime.now(),
                null
        );

        DeviceInfo deviceInfo = new DeviceInfo(
                deviceMacAddress,
                deviceFcmToken,
                deviceOsType,
                deviceOsVersion,
                List.of(appOrBrowserInfo)
        );

        AdminUserJpaEntity adminUserJpaEntity = new AdminUserJpaEntity(
                nickName,
                name,
                email,
                password,
                phoneNumber,
                new ArrayList<>(),
                Collections.singletonList(deviceInfo)
        );

        AdminUserJpaEntity savedAdminUserJpaEntity = adminUserJpaRepository.save(adminUserJpaEntity);

        return AdminUserResult.builder()
                .adminUserId(savedAdminUserJpaEntity.getId())
                .nickName(savedAdminUserJpaEntity.getNickName())
                .name(savedAdminUserJpaEntity.getName())
                .email(savedAdminUserJpaEntity.getEmail())
                .password(savedAdminUserJpaEntity.getPassword())
                .phoneNumber(savedAdminUserJpaEntity.getPhoneNumber())
                .imageUrl(savedAdminUserJpaEntity.getImageUrl())
                .adminUserRoles(savedAdminUserJpaEntity.getAdminUserRoles())
                .deviceInfos(savedAdminUserJpaEntity.getDeviceInfos())
                .createdAt(savedAdminUserJpaEntity.getCreatedAt())
                .modifiedAt(savedAdminUserJpaEntity.getModifiedAt())
                .build();
    }

    @Override
    public void modifyDeviceInfo(
            Long adminUserid,
            String deviceMacAddress,
            String deviceFcmToken,
            DeviceOsType deviceOsType,
            String deviceOsVersion,
            AppOrBrowserType appOrBrowserType,
            String appOrBrowserVersion,
            NetworkType networkType,
            String locale
    ) {
        AdminUserJpaEntity adminUserJpaEntity = getAdminJpaEntity(adminUserid);

        List<DeviceInfo> deviceInfos = adminUserJpaEntity.getDeviceInfos();
        boolean isDeviceExist = adminUserJpaEntity.getDeviceInfos().stream().anyMatch(deviceInfo -> deviceInfo.getDeviceManAddress().equals(deviceMacAddress));

        if (!isDeviceExist) {
            AppOrBrowserInfo appOrBrowserInfo = new AppOrBrowserInfo(
                    appOrBrowserType,
                    appOrBrowserVersion,
                    networkType,
                    locale,
                    LocalDateTime.now(),
                    null
            );
            DeviceInfo deviceInfo = new DeviceInfo(
                    deviceMacAddress,
                    deviceFcmToken,
                    deviceOsType,
                    deviceOsVersion,
                    List.of(appOrBrowserInfo)
            );
            deviceInfos.add(deviceInfo);
        } else {
            List<AppOrBrowserInfo> appOrBrowserInfos = adminUserJpaEntity.getDeviceInfos().stream().filter(deviceInfo -> deviceInfo.getDeviceManAddress().equals(deviceMacAddress)).toList()
                    .get(0).getAppOrBrowserInfos();

            boolean isAppOrBrowserExist = appOrBrowserInfos.stream().anyMatch(appOrBrowserInfo -> appOrBrowserInfo.getAppOrBrowserType().equals(appOrBrowserType));
            if (!isAppOrBrowserExist) {
                AppOrBrowserInfo appOrBrowserInfo = new AppOrBrowserInfo(
                        appOrBrowserType,
                        appOrBrowserVersion,
                        networkType,
                        locale,
                        LocalDateTime.now(),
                        null
                );
                appOrBrowserInfos.add(appOrBrowserInfo);
            } else {
                AppOrBrowserInfo appOrBrowserInfo = appOrBrowserInfos.stream().filter(it -> it.getAppOrBrowserType().equals(appOrBrowserType)).toList().get(0);
                appOrBrowserInfo.setAppOrBrowserVersion(appOrBrowserVersion);
                appOrBrowserInfo.setLocale(locale);
                appOrBrowserInfo.setNetworkType(networkType);
                appOrBrowserInfo.setLastLoginAt(LocalDateTime.now());

                appOrBrowserInfos.removeIf(it -> it.getAppOrBrowserType().equals(appOrBrowserType));
                appOrBrowserInfos.add(appOrBrowserInfo);
            }

            DeviceInfo savedDeviceInfo = new DeviceInfo(
                    deviceMacAddress,
                    deviceFcmToken,
                    deviceOsType,
                    deviceOsVersion,
                    appOrBrowserInfos
            );

            deviceInfos.removeIf(deviceInfo -> deviceInfo.getDeviceManAddress().equals(deviceMacAddress));
            deviceInfos.add(savedDeviceInfo);
        }

        adminUserJpaEntity.setDeviceInfos(deviceInfos);
        adminUserJpaRepository.save(adminUserJpaEntity);
    }

    @Override
    public void expireAdminUser(
            Long adminUserId,
            String deviceMacAddress,
            AppOrBrowserType appOrBrowserType
    ) {
        AdminUserJpaEntity adminUserJpaEntity = getAdminJpaEntity(adminUserId);

        List<DeviceInfo> deviceInfos = adminUserJpaEntity.getDeviceInfos();
        List<AppOrBrowserInfo> appOrBrowserInfos = adminUserJpaEntity.getDeviceInfos().stream().filter(deviceInfo -> deviceInfo.getDeviceManAddress().equals(deviceMacAddress)).toList()
                .get(0).getAppOrBrowserInfos();

        AppOrBrowserInfo appOrBrowserInfo = appOrBrowserInfos.stream().filter(it -> it.getAppOrBrowserType().equals(appOrBrowserType)).toList().get(0);
        appOrBrowserInfo.setLastLogoutAt(LocalDateTime.now());

        appOrBrowserInfos.removeIf(it -> it.getAppOrBrowserType().equals(appOrBrowserType));
        appOrBrowserInfos.add(appOrBrowserInfo);

        DeviceInfo deviceInfo = deviceInfos.stream().filter(it -> it.getDeviceManAddress().equals(deviceMacAddress)).toList().get(0);
        DeviceInfo savedDeviceInfo = new DeviceInfo(
                deviceMacAddress,
                deviceInfo.getDeviceFcmToken(),
                deviceInfo.getDeviceOsType(),
                deviceInfo.getDeviceOsVersion(),
                appOrBrowserInfos
        );

        deviceInfos.removeIf(it -> it.getDeviceManAddress().equals(deviceMacAddress));
        deviceInfos.add(savedDeviceInfo);

        adminUserJpaEntity.setDeviceInfos(deviceInfos);
        adminUserJpaRepository.save(adminUserJpaEntity);
    }

    @Override
    public AdminUserResult getAdminUserResult(
            String email,
            String password
    ) {
        AdminUserJpaEntity adminUserJpaEntity = adminUserJpaRepository.findFirstByEmailAndPassword(email, password).orElseThrow(() -> new ApiExceptionImpl(ErrorCodeImpl.NOT_FOUND));
        return AdminUserResult.builder()
                .adminUserId(adminUserJpaEntity.getId())
                .nickName(adminUserJpaEntity.getNickName())
                .name(adminUserJpaEntity.getName())
                .email(adminUserJpaEntity.getEmail())
                .password(adminUserJpaEntity.getPassword())
                .phoneNumber(adminUserJpaEntity.getPhoneNumber())
                .imageUrl(adminUserJpaEntity.getImageUrl())
                .adminUserRoles(adminUserJpaEntity.getAdminUserRoles())
                .deviceInfos(adminUserJpaEntity.getDeviceInfos())
                .createdAt(adminUserJpaEntity.getCreatedAt())
                .modifiedAt(adminUserJpaEntity.getModifiedAt())
                .build();
    }

    @Override
    public AdminUserResult addAdminUserRole(
            Long adminUserId,
            AdminUserRole targeAdminUserRole
    ) {
        AdminUserJpaEntity adminUserJpaEntity = getAdminJpaEntity(adminUserId);
        List<AdminUserRole> adminUserRoles = adminUserJpaEntity.getAdminUserRoles();
        if (!adminUserRoles.contains(targeAdminUserRole)) {
            adminUserRoles.add(targeAdminUserRole);
            adminUserJpaEntity.setAdminUserRoles(adminUserRoles);
            adminUserJpaEntity = adminUserJpaRepository.save(adminUserJpaEntity);
        }

        return AdminUserResult.builder()
                .adminUserId(adminUserJpaEntity.getId())
                .nickName(adminUserJpaEntity.getNickName())
                .name(adminUserJpaEntity.getName())
                .email(adminUserJpaEntity.getEmail())
                .password(adminUserJpaEntity.getPassword())
                .phoneNumber(adminUserJpaEntity.getPhoneNumber())
                .imageUrl(adminUserJpaEntity.getImageUrl())
                .adminUserRoles(adminUserJpaEntity.getAdminUserRoles())
                .deviceInfos(adminUserJpaEntity.getDeviceInfos())
                .createdAt(adminUserJpaEntity.getCreatedAt())
                .modifiedAt(adminUserJpaEntity.getModifiedAt())
                .build();
    }

    @Override
    public AdminUserResult removeAdminUserRole(Long adminUserId, AdminUserRole targetAdminUserRole) {
        AdminUserJpaEntity adminUserJpaEntity = getAdminJpaEntity(adminUserId);
        List<AdminUserRole> filterAdminUserRoles = adminUserJpaEntity.getAdminUserRoles().stream().filter(adminUserRole -> !adminUserRole.equals(targetAdminUserRole)).toList();
        adminUserJpaEntity.setAdminUserRoles(filterAdminUserRoles);
        AdminUserJpaEntity savedAdminUserJpaEntity = adminUserJpaRepository.save(adminUserJpaEntity);

        return AdminUserResult.builder()
                .adminUserId(savedAdminUserJpaEntity.getId())
                .nickName(savedAdminUserJpaEntity.getNickName())
                .name(savedAdminUserJpaEntity.getName())
                .email(savedAdminUserJpaEntity.getEmail())
                .password(savedAdminUserJpaEntity.getPassword())
                .phoneNumber(savedAdminUserJpaEntity.getPhoneNumber())
                .imageUrl(savedAdminUserJpaEntity.getImageUrl())
                .adminUserRoles(savedAdminUserJpaEntity.getAdminUserRoles())
                .deviceInfos(savedAdminUserJpaEntity.getDeviceInfos())
                .createdAt(savedAdminUserJpaEntity.getCreatedAt())
                .modifiedAt(savedAdminUserJpaEntity.getModifiedAt())
                .build();
    }

    private AdminUserJpaEntity getAdminJpaEntity(Long adminUserId) {
        return adminUserJpaRepository.findById(adminUserId).orElseThrow(() -> new ApiExceptionImpl(ErrorCodeImpl.NOT_FOUND));
    }
}
