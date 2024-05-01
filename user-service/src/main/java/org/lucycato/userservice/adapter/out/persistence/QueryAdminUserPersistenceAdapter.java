package org.lucycato.userservice.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import org.lucycato.common.annotation.hexagonal.out.PersistenceAdapter;
import org.lucycato.common.error.ErrorCodeImpl;
import org.lucycato.common.exception.ApiExceptionImpl;
import org.lucycato.mvc.CommonRedisTemplate;
import org.lucycato.userservice.adapter.out.persistence.jpaentity.AdminUserJpaEntity;
import org.lucycato.userservice.adapter.out.persistence.jparepository.AdminUserJpaRepository;
import org.lucycato.userservice.adapter.out.persistence.jpaentity.AppUserJpaEntity;
import org.lucycato.userservice.adapter.out.persistence.jparepository.AppUserJpaRepository;
import org.lucycato.userservice.adapter.out.persistence.redisentity.AppUserMembershipRedisEntity;
import org.lucycato.userservice.adapter.out.persistence.redisentity.AppUserRedisEntity;
import org.lucycato.userservice.adapter.out.persistence.vo.DeviceVo;
import org.lucycato.userservice.application.port.out.QueryAdminUserPort;
import org.lucycato.userservice.application.port.out.result.AdminUserResult;
import org.lucycato.userservice.application.port.out.result.AppUserResult;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

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
