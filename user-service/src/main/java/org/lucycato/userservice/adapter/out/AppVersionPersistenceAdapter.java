package org.lucycato.userservice.adapter.out;

import lombok.RequiredArgsConstructor;
import org.lucycato.common.annotation.hexagonal.out.PersistenceAdapter;
import org.lucycato.common.error.ErrorCodeImpl;
import org.lucycato.common.exception.ApiExceptionImpl;
import org.lucycato.userservice.application.port.out.AppVersionPort;
import org.lucycato.userservice.application.port.out.response.AppVersionResponse;

@PersistenceAdapter
@RequiredArgsConstructor
public class AppVersionPersistenceAdapter implements AppVersionPort {
    private final AppVersionJpaRepository appVersionJpaRepository;

    @Override
    public AppVersionResponse getStandardAppVersion() {
        AppVersionJpaEntity appVersionJpaEntity = appVersionJpaRepository.findFirstOrderByIdDesc().orElseThrow(() -> new ApiExceptionImpl(ErrorCodeImpl.NULL_POINT));
        return AppVersionResponse.builder()
                .standardAppVersion(appVersionJpaEntity.getAppVersion())
                .build();
    }
}
