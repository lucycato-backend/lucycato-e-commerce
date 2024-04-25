package org.lucycato.userservice.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import org.lucycato.common.annotation.hexagonal.out.PersistenceAdapter;
import org.lucycato.common.error.ErrorCodeImpl;
import org.lucycato.common.exception.ApiExceptionImpl;
import org.lucycato.common.security.AdminUserRole;
import org.lucycato.mvc.CommonRedisTemplate;
import org.lucycato.userservice.adapter.out.persistence.entity.AdminUserJpaEntity;
import org.lucycato.userservice.application.port.out.AdminUserPort;
import org.lucycato.userservice.application.port.out.result.AdminUserResult;
import org.lucycato.userservice.model.enums.DeviceOsType;
import org.lucycato.userservice.model.info.DeviceInfo;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

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
            String deviceOsVersion
    ) {
        DeviceInfo deviceInfo = new DeviceInfo(
                deviceMacAddress,
                deviceFcmToken,
                deviceOsType,
                deviceOsVersion
        );
        AdminUserJpaEntity adminUserJpaEntity = new AdminUserJpaEntity(
                nickName,
                name,
                email,
                password,
                phoneNumber,
                new ArrayList<>(),
                Collections.singletonList(deviceInfo),
                LocalDateTime.now()
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
                .lastLoginAt(savedAdminUserJpaEntity.getLastLoginAt())
                .lastLogoutAt(savedAdminUserJpaEntity.getLastLogoutAt())
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
            String deviceOsVersion
    ) {
        AdminUserJpaEntity adminUserJpaEntity = getAdminJpaEntity(adminUserid);
        List<DeviceInfo> deviceInfos = adminUserJpaEntity.getDeviceInfos().stream()
                .filter(deviceInfo -> deviceInfo.getDeviceManAddress().equals(deviceMacAddress))
                .toList();

        if (deviceInfos.isEmpty()) {
            DeviceInfo deviceInfo = new DeviceInfo(
                    deviceMacAddress,
                    deviceFcmToken,
                    deviceOsType,
                    deviceOsVersion
            );
            adminUserJpaEntity.getDeviceInfos().add(deviceInfo);
        } else {
            adminUserJpaEntity.getDeviceInfos().removeIf(deviceInfo -> deviceInfo.getDeviceManAddress().equals(deviceMacAddress));

            DeviceInfo deviceInfo = new DeviceInfo(
                    deviceMacAddress,
                    deviceFcmToken,
                    deviceOsType,
                    deviceOsVersion
            );

            adminUserJpaEntity.getDeviceInfos().add(deviceInfo);
            adminUserJpaEntity.setDeviceInfos(adminUserJpaEntity.getDeviceInfos());
        }

        adminUserJpaRepository.save(adminUserJpaEntity);
    }

    @Override
    public void expireAdminUser(Long adminUserId) {
        AdminUserJpaEntity adminUserJpaEntity = getAdminJpaEntity(adminUserId);
        adminUserJpaEntity.setLastLogoutAt(LocalDateTime.now());
        adminUserJpaRepository.save(adminUserJpaEntity);
    }

    @Override
    public AdminUserResult getAdminUserResult(
            String email,
            String password
    ) {
        AdminUserJpaEntity adminUserJpaEntity = adminUserJpaRepository.findFirstByEmailAndPassword(email, password).orElseThrow(() -> new ApiExceptionImpl(ErrorCodeImpl.NULL_POINT));
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
                .lastLoginAt(adminUserJpaEntity.getLastLoginAt())
                .lastLogoutAt(adminUserJpaEntity.getLastLogoutAt())
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
                .lastLoginAt(adminUserJpaEntity.getLastLoginAt())
                .lastLogoutAt(adminUserJpaEntity.getLastLogoutAt())
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
                .lastLoginAt(savedAdminUserJpaEntity.getLastLoginAt())
                .lastLogoutAt(savedAdminUserJpaEntity.getLastLogoutAt())
                .createdAt(savedAdminUserJpaEntity.getCreatedAt())
                .modifiedAt(savedAdminUserJpaEntity.getModifiedAt())
                .build();
    }

    private AdminUserJpaEntity getAdminJpaEntity(Long adminUserId) {
        return adminUserJpaRepository.findById(adminUserId).orElseThrow(() -> new ApiExceptionImpl(ErrorCodeImpl.NULL_POINT));
    }
}
