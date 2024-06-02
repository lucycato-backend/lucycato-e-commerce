package org.lucycato.userauthcommandservice.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import org.lucycato.common.annotation.hexagonal.out.PersistenceAdapter;
import org.lucycato.common.error.ErrorCodeImpl;
import org.lucycato.common.exception.ApiExceptionImpl;
import org.lucycato.userauthcommandservice.adapter.out.persistence.entity.AdminUserJpaEntity;
import org.lucycato.userauthcommandservice.adapter.out.persistence.repository.AdminUserJpaRepository;
import org.lucycato.userauthcommandservice.adapter.out.persistence.repository.QAdminUserRepository;
import org.lucycato.userauthcommandservice.adapter.out.persistence.vo.DeviceVo;
import org.lucycato.userauthcommandservice.application.port.out.QueryAdminUserPort;
import org.lucycato.userauthcommandservice.application.port.out.result.AdminUserResult;

import java.util.List;
import java.util.Optional;

@PersistenceAdapter
@RequiredArgsConstructor
public class QueryAdminUserPersistenceAdapter implements QueryAdminUserPort {
    private final AdminUserJpaRepository adminUserJpaRepository;
    private final QAdminUserRepository qAdminUserRepository;

    @Override
    public AdminUserResult getAdminUser(Long adminUserId) {
        AdminUserJpaEntity adminUserJpaEntity = adminUserJpaRepository.findById(adminUserId).orElseThrow(() -> new ApiExceptionImpl(ErrorCodeImpl.NOT_FOUND));
        return AdminUserResult.from(adminUserJpaEntity);
    }

    @Override
    public Optional<AdminUserResult> getAdminUser(String name, String phoneNumber) {
        Optional<AdminUserJpaEntity> adminUserJpaEntity = qAdminUserRepository.findOneByEmailAndPassword(name, phoneNumber);
        return adminUserJpaEntity.map(AdminUserResult::from);
    }

    @Override
    public List<DeviceVo> getAdminUserDeviceInfoList(Long adminUserId) {
        return (List<DeviceVo>) adminUserJpaRepository.findDeviceInfosByAppUserId(adminUserId).orElseThrow(() -> new ApiExceptionImpl(ErrorCodeImpl.NOT_FOUND));
    }
}
