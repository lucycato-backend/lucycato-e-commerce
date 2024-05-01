package org.lucycato.userservice.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import org.lucycato.common.annotation.hexagonal.out.PersistenceAdapter;
import org.lucycato.common.error.ErrorCodeImpl;
import org.lucycato.common.exception.ApiExceptionImpl;
import org.lucycato.mvc.CommonRedisTemplate;
import org.lucycato.userservice.adapter.out.persistence.entity.AdminUserJpaEntity;
import org.lucycato.userservice.adapter.out.persistence.repository.AdminUserJpaRepository;
import org.lucycato.userservice.adapter.out.persistence.entity.AppUserJpaEntity;
import org.lucycato.userservice.adapter.out.persistence.repository.AppUserJpaRepository;
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
    private final String APP_USER_CACHE_KEY = "app-users:%d";

    private final AdminUserJpaRepository adminUserJpaRepository;

    private final AppUserJpaRepository appUserJpaRepository;

    private final CommonRedisTemplate commonRedisTemplate;

    @Override
    public AdminUserResult getAdminUser(Long adminUserId) {
        AdminUserJpaEntity adminUserJpaEntity = adminUserJpaRepository.findById(adminUserId).orElseThrow(() -> new ApiExceptionImpl(ErrorCodeImpl.NOT_FOUND));
        return AdminUserResult.from(adminUserJpaEntity);
    }

    @Override
    public AppUserResult getAppUser(Long appUserId) {
        return commonRedisTemplate.<AppUserResult>find(APP_USER_CACHE_KEY.formatted(appUserId)).orElseGet(() -> {
            AppUserJpaEntity appUserJpaEntity = appUserJpaRepository.findById(appUserId).orElseThrow(() -> new ApiExceptionImpl(ErrorCodeImpl.NOT_FOUND));
            return AppUserResult.from(appUserJpaEntity);
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
        List<String> keys = appUserIds.stream().map(APP_USER_CACHE_KEY::formatted).toList();
        List<AppUserResult> redisAppUserResults = commonRedisTemplate.findAll(keys);

        List<Long> emptyAppUserIds = new ArrayList<>();
        for (int i = 0; i < redisAppUserResults.size(); i++) {
            if (redisAppUserResults.get(i) == null) {
                emptyAppUserIds.add(appUserIds.get(i));
            }
        }

        List<AppUserResult> dbAppUserResults = appUserJpaRepository.findByIds(emptyAppUserIds)
                .stream()
                .map(AppUserResult::from)
                .toList();

        dbAppUserResults.addAll(redisAppUserResults);
        dbAppUserResults.removeIf(Objects::isNull);
        dbAppUserResults.sort(Comparator.comparingLong(AppUserResult::getAppUserId).reversed());

        return dbAppUserResults;
    }

    @Override
    public List<DeviceVo> getAppUserDeviceInfoList(Long adminUserId) {
        return (List<DeviceVo>) adminUserJpaRepository.findDeviceInfosByAppUserId(adminUserId).orElseThrow(() -> new ApiExceptionImpl(ErrorCodeImpl.NOT_FOUND));
    }
}
