package org.lucycato.userservice.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import org.lucycato.common.annotation.hexagonal.out.PersistenceAdapter;
import org.lucycato.common.error.ErrorCodeImpl;
import org.lucycato.common.exception.ApiExceptionImpl;
import org.lucycato.mvc.CommonRedisTemplate;
import org.lucycato.userservice.adapter.out.persistence.jpaentity.AppUserJpaEntity;
import org.lucycato.userservice.adapter.out.persistence.jparepository.AppUserJpaRepository;
import org.lucycato.userservice.adapter.out.persistence.redisentity.AppUserMembershipRedisEntity;
import org.lucycato.userservice.adapter.out.persistence.redisentity.AppUserRedisEntity;
import org.lucycato.userservice.adapter.out.persistence.vo.DeviceVo;
import org.lucycato.userservice.application.port.out.QueryAppUserPort;
import org.lucycato.userservice.application.port.out.result.AppUserResult;

import java.util.List;

@PersistenceAdapter
@RequiredArgsConstructor
public class QueryAppUserPersistenceAdapter implements QueryAppUserPort {
    private final String APP_USER_REDIS_KEY = "app-users:%d";
    private final String APP_USER_MEMBERSHIP_REDIS_ALL_KEY = "app-users:%d:membership*";

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
    public AppUserResult getAppUserResult(Long appUserId) {
        AppUserRedisEntity appUserRedisEntity = commonRedisTemplate.find(APP_USER_REDIS_KEY.formatted(appUserId));

        if (appUserRedisEntity != null) {
            List<String> keys = commonRedisTemplate.keyScan(APP_USER_MEMBERSHIP_REDIS_ALL_KEY.formatted(appUserId),10);
            List<AppUserMembershipRedisEntity> appUserMembershipRedisEntities = commonRedisTemplate.findAll(keys);
            return AppUserResult.from(appUserRedisEntity, appUserMembershipRedisEntities);
        } else {
            AppUserJpaEntity appUserJpaEntity = appUserJpaRepository.findById(appUserId).orElseThrow(() -> new ApiExceptionImpl(ErrorCodeImpl.NOT_FOUND));
            return AppUserResult.from(appUserJpaEntity);
        }
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
