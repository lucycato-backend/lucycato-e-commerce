package org.lucycato.userservice.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import org.lucycato.common.annotation.hexagonal.out.PersistenceAdapter;
import org.lucycato.common.error.ErrorCodeImpl;
import org.lucycato.common.exception.ApiExceptionImpl;
import org.lucycato.userservice.adapter.out.persistence.entity.AdminUserJpaEntity;
import org.lucycato.userservice.adapter.out.persistence.repository.AdminUserJpaRepository;
import org.lucycato.userservice.adapter.out.persistence.vo.DeviceVo;
import org.lucycato.userservice.application.port.out.QueryAdminUserPort;
import org.lucycato.userservice.application.port.out.result.AdminUserResult;
import org.lucycato.userservice.domain.AdminUserProfile;

import java.util.List;
import java.util.Optional;

@PersistenceAdapter
@RequiredArgsConstructor
public class QueryAdminUserPersistenceAdapter implements QueryAdminUserPort {
    private final AdminUserJpaRepository adminUserJpaRepository;

    @Override
    public AdminUserResult getAdminUser(Long adminUserId) {
        AdminUserJpaEntity adminUserJpaEntity = adminUserJpaRepository.findById(adminUserId).orElseThrow(() -> new ApiExceptionImpl(ErrorCodeImpl.NOT_FOUND));
        return AdminUserResult.from(adminUserJpaEntity);
    }

    @Override
    public Optional<AdminUserProfile> getAdminUserProfile(String name, String phoneNumber) {
        Optional<AdminUserJpaEntity> adminUserJpaEntity = adminUserJpaRepository.findOneByNameAndPhoneNumber(name, phoneNumber);
        return adminUserJpaEntity.map(AdminUserProfile::from);
    }

    @Override
    public List<DeviceVo> getAdminUserDeviceInfoList(Long adminUserId) {
        return (List<DeviceVo>) adminUserJpaRepository.findDeviceInfosByAppUserId(adminUserId).orElseThrow(() -> new ApiExceptionImpl(ErrorCodeImpl.NOT_FOUND));
    }
}
