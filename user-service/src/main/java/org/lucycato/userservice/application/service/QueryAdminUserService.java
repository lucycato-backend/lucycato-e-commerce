package org.lucycato.userservice.application.service;

import org.lucycato.userservice.application.port.in.QueryAdminUserUseCase;
import org.lucycato.userservice.application.port.in.command.*;
import org.lucycato.userservice.domain.AdminUser;
import org.lucycato.userservice.domain.AppUser;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QueryAdminUserService implements QueryAdminUserUseCase {

    @Override
    public AdminUser getAdminUser(GetAdminUserCommand command) {
        return null;
    }

    @Override
    public AppUser getAppUser(GetAppUserCommand command) {
        return null;
    }

    @Override
    public List<AppUser> getAppUserList() {
        return null;
    }

    @Override
    public List<AppUser> getAppUserByLectureId(GetAppUserListByLectureIdsCommand command) {
        return null;
    }

    @Override
    public List<AppUser> getAppUserByTeacherId(GetAppUserListByTeacherIdsCommand command) {
        return null;
    }

    @Override
    public List<AppUser> getAppUserListByRequestDelegationRoles(GetAppUserByRequestDelegationRolesCommand command) {
        return null;
    }
}
