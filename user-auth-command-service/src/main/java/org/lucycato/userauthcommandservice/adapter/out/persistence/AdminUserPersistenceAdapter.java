package org.lucycato.userauthcommandservice.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import org.lucycato.common.annotation.hexagonal.out.PersistenceAdapter;
import org.lucycato.common.error.ErrorCodeImpl;
import org.lucycato.common.exception.ApiExceptionImpl;
import org.lucycato.common.security.AdminUserRole;
import org.lucycato.userauthcommandservice.adapter.out.persistence.entity.AdminUserJpaEntity;
import org.lucycato.userauthcommandservice.adapter.out.persistence.repository.AdminUserJpaRepository;
import org.lucycato.userauthcommandservice.application.port.out.AdminUserPort;
import org.lucycato.userauthcommandservice.application.port.out.result.AdminUserResult;
import org.lucycato.userauthcommandservice.application.port.out.result.AdminfindPasswordResult;

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
    public AdminUserResult getAdminUserResult(
            String email
    ) {
        AdminUserJpaEntity adminUserJpaEntity = adminUserJpaRepository.findFirstByEmail(email).orElseThrow(() -> new ApiExceptionImpl(ErrorCodeImpl.NOT_FOUND));
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

    @Override
    public void setTempPasswordToRedis(String tempPassword, String email) {
        // redis에 데이터 set 부분 구현 필요. 현재 메소드에서 redis 연동 바로 하면 되는지?
    }

    @Override
    public AdminfindPasswordResult getTempPasswordToRedis(String email) {
        // redis에 데이터 get 부분 구현 필요. 현재 메소드에서 redis 연동 바로 하면 되는지?
        return new AdminfindPasswordResult("","");
    }

    @Override
    public void sendTempPasswordByEmail(String tempPassword, String email) {
         // send mail 구현 필요. 현재 메소드에서 send mail 바로 하면 되는지?
    }
}
