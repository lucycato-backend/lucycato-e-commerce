package org.lucycato.productqueryservice.adapter.out.service;

import lombok.RequiredArgsConstructor;
import org.lucycato.common.annotation.hexagonal.out.PersistenceAdapter;
import org.lucycato.common.annotation.hexagonal.out.ServiceAdapter;
import org.lucycato.productqueryservice.application.port.out.UserAuthPort;
import reactor.core.publisher.Mono;

@ServiceAdapter
@RequiredArgsConstructor
public class UserAuthServiceAdapter implements UserAuthPort {

    @Override
    public Mono<Boolean> checkAdminUserTeacherAssistance(Long adminUserId) {
        return null;
    }
}
