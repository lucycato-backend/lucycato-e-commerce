package org.lucycato.userservice.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import org.lucycato.common.annotation.hexagonal.out.PersistenceAdapter;
import org.lucycato.common.error.ErrorCodeImpl;
import org.lucycato.common.exception.ApiExceptionImpl;
import org.lucycato.userservice.adapter.out.persistence.entity.AdminUserJpaEntity;
import org.lucycato.userservice.adapter.out.persistence.entity.AppUserJpaEntity;
import org.lucycato.userservice.adapter.out.persistence.vo.DeviceVo;
import org.lucycato.userservice.adapter.out.persistence.vo.PlatformVo;
import org.lucycato.userservice.adapter.out.persistence.repository.AdminUserJpaRepository;
import org.lucycato.userservice.adapter.out.persistence.repository.AppUserJpaRepository;
import org.lucycato.userservice.application.port.out.DeviceManagementPort;
import org.lucycato.userservice.domain.enums.PlatformType;
import org.lucycato.userservice.domain.enums.DeviceOsType;
import org.lucycato.userservice.domain.enums.NetworkType;

import java.time.LocalDateTime;
import java.util.List;

@PersistenceAdapter
@RequiredArgsConstructor
public class DeviceManagementPersistenceAdapter implements DeviceManagementPort {
    private final AdminUserJpaRepository adminUserJpaRepository;

    private final AppUserJpaRepository appUserJpaRepository;

    @Override
    public void modifyAdminUserDeviceInfo(
            Long adminUserId,
            String deviceMacAddress,
            String deviceFcmToken,
            DeviceOsType deviceOsType,
            String deviceOsVersion,
            PlatformType platformType,
            String platformVersion,
            NetworkType networkType,
            String locale
    ) {
        AdminUserJpaEntity adminUserJpaEntity = getAdminJpaEntity(adminUserId);

        List<DeviceVo> deviceVos = adminUserJpaEntity.getDeviceVos();
        List<DeviceVo> modifiedDeviceVos = modifyDeviceInfo(
                deviceVos,
                deviceMacAddress,
                deviceFcmToken,
                deviceOsType,
                deviceOsVersion,
                platformType,
                platformVersion,
                networkType,
                locale
        );

        adminUserJpaEntity.setDeviceVos(modifiedDeviceVos);
        adminUserJpaRepository.save(adminUserJpaEntity);
    }

    @Override
    public void modifyAppUserDeviceInfo(Long appUserId, String deviceMacAddress, String deviceFcmToken, DeviceOsType deviceOsType, String deviceOsVersion, PlatformType platformType, String platformVersion, NetworkType networkType, String locale) {
        AppUserJpaEntity appUserJpaEntity = getAppUserJptEntity(appUserId);

        List<DeviceVo> deviceVos = appUserJpaEntity.getDeviceVos();
        List<DeviceVo> modifiedDeviceVos = modifyDeviceInfo(
                deviceVos,
                deviceMacAddress,
                deviceFcmToken,
                deviceOsType,
                deviceOsVersion,
                platformType,
                platformVersion,
                networkType,
                locale
        );

        appUserJpaEntity.setDeviceVos(modifiedDeviceVos);
        appUserJpaRepository.save(appUserJpaEntity);
    }

    @Override
    public void modifyAdminUserDeviceLogout(Long adminUserId, String deviceMacAddress, PlatformType platformType) {
        AdminUserJpaEntity adminUserJpaEntity = getAdminJpaEntity(adminUserId);

        List<DeviceVo> deviceVos = adminUserJpaEntity.getDeviceVos();
        List<DeviceVo> modifiedDeviceVos = modifyDeviceLogout(deviceVos, deviceMacAddress, platformType);

        adminUserJpaEntity.setDeviceVos(modifiedDeviceVos);
        adminUserJpaRepository.save(adminUserJpaEntity);
    }

    @Override
    public void modifyAppUserDeviceLogout(Long appUserId, String deviceMacAddress, PlatformType platformType) {
        AppUserJpaEntity appUserJpaEntity = getAppUserJptEntity(appUserId);

        List<DeviceVo> deviceVos = appUserJpaEntity.getDeviceVos();
        List<DeviceVo> modifiedDeviceVos = modifyDeviceLogout(deviceVos, deviceMacAddress, platformType);

        appUserJpaEntity.setDeviceVos(modifiedDeviceVos);
        appUserJpaRepository.save(appUserJpaEntity);
    }

    private List<DeviceVo> modifyDeviceInfo(
            List<DeviceVo> deviceVos,
            String deviceMacAddress,
            String deviceFcmToken,
            DeviceOsType deviceOsType,
            String deviceOsVersion,
            PlatformType platformType,
            String platformVersion,
            NetworkType networkType,
            String locale

    ) {
        boolean isDeviceExist = deviceVos.stream().anyMatch(deviceInfo -> deviceInfo.getDeviceManAddress().equals(deviceMacAddress));

        if (!isDeviceExist) {
            PlatformVo platformVo = new PlatformVo(
                    platformType,
                    platformVersion,
                    networkType,
                    locale,
                    LocalDateTime.now(),
                    null
            );

            DeviceVo deviceVo = new DeviceVo(
                    deviceMacAddress,
                    deviceFcmToken,
                    deviceOsType,
                    deviceOsVersion,
                    List.of(platformVo)
            );
            deviceVos.add(deviceVo);
        } else {
            List<PlatformVo> platformVos = deviceVos.stream().filter(deviceInfo -> deviceInfo.getDeviceManAddress().equals(deviceMacAddress)).toList()
                    .get(0).getPlatformVos();

            boolean isPlatformExist = platformVos.stream().anyMatch(platformVo -> platformVo.getPlatformType().equals(platformType));
            if (!isPlatformExist) {
                PlatformVo platformVo = new PlatformVo(
                        platformType,
                        platformVersion,
                        networkType,
                        locale,
                        LocalDateTime.now(),
                        null
                );
                platformVos.add(platformVo);
            } else {
                PlatformVo platformVo = platformVos.stream().filter(it -> it.getPlatformType().equals(platformType)).toList().get(0);
                platformVo.setPlatformVersion(platformVersion);
                platformVo.setLocale(locale);
                platformVo.setNetworkType(networkType);
                platformVo.setLastLoginAt(LocalDateTime.now());

                platformVos.removeIf(it -> it.getPlatformType().equals(platformType));
                platformVos.add(platformVo);
            }

            DeviceVo savedDeviceVo = new DeviceVo(
                    deviceMacAddress,
                    deviceFcmToken,
                    deviceOsType,
                    deviceOsVersion,
                    platformVos
            );

            deviceVos.removeIf(deviceInfo -> deviceInfo.getDeviceManAddress().equals(deviceMacAddress));
            deviceVos.add(savedDeviceVo);
        }

        return deviceVos;
    }

    private List<DeviceVo> modifyDeviceLogout(List<DeviceVo> deviceVos, String deviceMacAddress, PlatformType platformType) {
        List<PlatformVo> platformVos = deviceVos.stream().filter(deviceInfo -> deviceInfo.getDeviceManAddress().equals(deviceMacAddress)).toList()
                .get(0).getPlatformVos();

        PlatformVo platformVo = platformVos.stream().filter(it -> it.getPlatformType().equals(platformType)).toList().get(0);
        platformVo.setLastLogoutAt(LocalDateTime.now());

        platformVos.removeIf(it -> it.getPlatformType().equals(platformType));
        platformVos.add(platformVo);

        DeviceVo deviceVo = deviceVos.stream().filter(it -> it.getDeviceManAddress().equals(deviceMacAddress)).toList().get(0);
        DeviceVo savedDeviceVo = new DeviceVo(
                deviceMacAddress,
                deviceVo.getDeviceFcmToken(),
                deviceVo.getDeviceOsType(),
                deviceVo.getDeviceOsVersion(),
                platformVos
        );

        deviceVos.removeIf(it -> it.getDeviceManAddress().equals(deviceMacAddress));
        deviceVos.add(savedDeviceVo);

        return deviceVos;
    }

    private AdminUserJpaEntity getAdminJpaEntity(Long adminUserId) {
        return adminUserJpaRepository.findById(adminUserId).orElseThrow(() -> new ApiExceptionImpl(ErrorCodeImpl.NOT_FOUND));
    }

    private AppUserJpaEntity getAppUserJptEntity(Long appUserId) {
        return appUserJpaRepository.findById(appUserId).orElseThrow(() -> new ApiExceptionImpl(ErrorCodeImpl.NOT_FOUND));
    }
}
