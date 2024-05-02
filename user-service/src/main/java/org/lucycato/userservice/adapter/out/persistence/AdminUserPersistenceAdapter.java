package org.lucycato.userservice.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import org.lucycato.common.annotation.hexagonal.out.PersistenceAdapter;
import org.lucycato.common.error.ErrorCodeImpl;
import org.lucycato.common.exception.ApiExceptionImpl;
import org.lucycato.common.security.AdminUserRole;
import org.lucycato.userservice.adapter.out.persistence.entity.AdminUserJpaEntity;
import org.lucycato.userservice.adapter.out.persistence.repository.AdminUserJpaRepository;
import org.lucycato.userservice.application.port.out.AdminUserPort;
import org.lucycato.userservice.application.port.out.result.AdminUserResult;

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
            String phoneNumber

    ) {
        AdminUserJpaEntity adminUserJpaEntity = new AdminUserJpaEntity(
                nickName,
                name,
                email,
                password,
                phoneNumber
        );

        AdminUserJpaEntity savedAdminUserJpaEntity = adminUserJpaRepository.save(adminUserJpaEntity);

        return AdminUserResult.from(savedAdminUserJpaEntity);
    }

    @Override
    public AdminUserResult getAdminUserResult(
            String email,
            String password
    ) {
        AdminUserJpaEntity adminUserJpaEntity = adminUserJpaRepository.findFirstByEmailAndPassword(email, password).orElseThrow(() -> new ApiExceptionImpl(ErrorCodeImpl.NOT_FOUND));
        return AdminUserResult.from(adminUserJpaEntity);
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

        return AdminUserResult.from(adminUserJpaEntity);
    }

    @Override
    public AdminUserResult removeAdminUserRole(Long adminUserId, AdminUserRole targetAdminUserRole) {
        AdminUserJpaEntity adminUserJpaEntity = getAdminJpaEntity(adminUserId);
        List<AdminUserRole> filterAdminUserRoles = adminUserJpaEntity.getAdminUserRoles().stream().filter(adminUserRole -> !adminUserRole.equals(targetAdminUserRole)).toList();
        adminUserJpaEntity.setAdminUserRoles(filterAdminUserRoles);
        AdminUserJpaEntity savedAdminUserJpaEntity = adminUserJpaRepository.save(adminUserJpaEntity);

        return AdminUserResult.from(savedAdminUserJpaEntity);
    }

    private AdminUserJpaEntity getAdminJpaEntity(Long adminUserId) {
        return adminUserJpaRepository.findById(adminUserId).orElseThrow(() -> new ApiExceptionImpl(ErrorCodeImpl.NOT_FOUND));
    }
}
