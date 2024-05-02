package org.lucycato.userservice.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import org.lucycato.common.annotation.hexagonal.out.PersistenceAdapter;
import org.lucycato.common.error.ErrorCodeImpl;
import org.lucycato.common.exception.ApiExceptionImpl;
import org.lucycato.userservice.adapter.out.persistence.entity.AdminUserJpaEntity;
import org.lucycato.userservice.adapter.out.persistence.repository.AdminUserJpaRepository;
import org.lucycato.userservice.adapter.out.persistence.redisentity.AppUserMembershipRedisEntity;
import org.lucycato.userservice.adapter.out.persistence.redisentity.AppUserRedisEntity;
import org.lucycato.userservice.adapter.out.persistence.vo.DeviceVo;
import org.lucycato.userservice.application.port.out.QueryAdminUserPort;
import org.lucycato.userservice.application.port.out.result.AdminUserResult;

import java.util.List;

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
    public List<DeviceVo> getAdminUserDeviceInfoList(Long adminUserId) {
        return (List<DeviceVo>) adminUserJpaRepository.findDeviceInfosByAppUserId(adminUserId).orElseThrow(() -> new ApiExceptionImpl(ErrorCodeImpl.NOT_FOUND));
    }
}
