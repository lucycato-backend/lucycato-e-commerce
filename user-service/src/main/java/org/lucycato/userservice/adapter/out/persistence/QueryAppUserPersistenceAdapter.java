package org.lucycato.userservice.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import org.lucycato.common.annotation.hexagonal.out.PersistenceAdapter;
import org.lucycato.common.error.ErrorCodeImpl;
import org.lucycato.common.exception.ApiExceptionImpl;
import org.lucycato.mvc.CommonRedisTemplate;
import org.lucycato.userservice.adapter.out.persistence.jpaentity.AppUserJpaEntity;
import org.lucycato.userservice.adapter.out.persistence.jparepository.AppUserJpaRepository;
import org.lucycato.userservice.adapter.out.persistence.vo.DeviceVo;
import org.lucycato.userservice.application.port.out.QueryAppUserPort;
import org.lucycato.userservice.application.port.out.result.AppUserResult;

import java.util.List;
import java.util.concurrent.TimeUnit;

@PersistenceAdapter
@RequiredArgsConstructor
public class QueryAppUserPersistenceAdapter implements QueryAppUserPort {
    private final String APP_USER_REDIS_KEY = "app-users:%d";

    private final AppUserJpaRepository appUserJpaRepository;

    private final CommonRedisTemplate commonRedisTemplate;

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
    public AppUserResult getAppUserResult(Long appUserId) {;
        return commonRedisTemplate.<AppUserResult>find(APP_USER_REDIS_KEY.formatted(appUserId))
                .orElseGet(() -> {
                    AppUserJpaEntity appUserJpaEntity = appUserJpaRepository.findById(appUserId).orElseThrow(() -> new ApiExceptionImpl(ErrorCodeImpl.NOT_FOUND));
                    AppUserResult appUserResult = AppUserResult.from(appUserJpaEntity);
                    commonRedisTemplate.save(APP_USER_REDIS_KEY.formatted(appUserId), appUserResult, 30L, TimeUnit.DAYS);
                    return appUserResult;
                });
    }

    @Override
    public List<AppUserResult> getAppUserList() {
        return appUserJpaRepository.findAll()
                .stream()
                .map(AppUserResult::from)
                .toList();
    }

    @Override
    public List<AppUserResult> getAppUserListByUserIds(List<Long> appUserIds) {
        return appUserJpaRepository.findByIds(appUserIds)
                .stream()
                .map(AppUserResult::from)
                .toList();
    }

    @Override
    public List<DeviceVo> getAppUserDeviceInfoList(Long appUserId) {
        return (List<DeviceVo>) appUserJpaRepository.findDeviceInfosByAppUserId(appUserId).orElseThrow(() -> new ApiExceptionImpl(ErrorCodeImpl.NOT_FOUND));
    }
}
