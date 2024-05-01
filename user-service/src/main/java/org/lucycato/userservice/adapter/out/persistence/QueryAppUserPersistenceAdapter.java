package org.lucycato.userservice.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import org.lucycato.common.annotation.hexagonal.out.PersistenceAdapter;
import org.lucycato.common.error.ErrorCodeImpl;
import org.lucycato.common.exception.ApiExceptionImpl;
import org.lucycato.userservice.adapter.out.persistence.entity.AppUserJpaEntity;
import org.lucycato.userservice.adapter.out.persistence.repository.AppUserJpaRepository;
import org.lucycato.userservice.adapter.out.persistence.vo.DeviceVo;
import org.lucycato.userservice.application.port.out.QueryAppUserPort;
import org.lucycato.userservice.application.port.out.result.AppUserResult;

import java.util.List;

@PersistenceAdapter
@RequiredArgsConstructor
public class QueryAppUserPersistenceAdapter implements QueryAppUserPort {

    private final AppUserJpaRepository appUserJpaRepository;

    @Override
    public AppUserResult getAppUserResultByEmail(String email) {
        AppUserJpaEntity appUserJpaEntity = appUserJpaRepository.findFirstByEmail(email).orElseThrow(() -> new ApiExceptionImpl(ErrorCodeImpl.NOT_FOUND));

        return AppUserResult.from(appUserJpaEntity);
    }

    @Override
    public AppUserResult getAppUserResultByPhoneNumber(String phoneNumber) {
        AppUserJpaEntity appUserJpaEntity = appUserJpaRepository.findFirstByPhoneNumber(phoneNumber).orElseThrow(() -> new ApiExceptionImpl(ErrorCodeImpl.NOT_FOUND));

        return AppUserResult.from(appUserJpaEntity);
    }

    @Override
    public AppUserResult getAppUserResult(Long appUserId) {
        AppUserJpaEntity appUserJpaEntity = appUserJpaRepository.findById(appUserId).orElseThrow(() -> new ApiExceptionImpl(ErrorCodeImpl.NOT_FOUND));

        return AppUserResult.from(appUserJpaEntity);
    }

    @Override
    public List<DeviceVo> getAppUserDeviceInfoList(Long appUserId) {
        return (List<DeviceVo>) appUserJpaRepository.findDeviceInfosByAppUserId(appUserId).orElseThrow(() -> new ApiExceptionImpl(ErrorCodeImpl.NOT_FOUND));
    }
}
