package org.lucycato.productqueryservice.adapter.out.service;

import lombok.RequiredArgsConstructor;
import org.lucycato.common.annotation.hexagonal.out.ServiceAdapter;
import org.lucycato.productqueryservice.application.port.out.UserAuthPort;
import org.lucycato.webflux.CommonWebClient;
import reactor.core.publisher.Mono;

@ServiceAdapter
@RequiredArgsConstructor
public class UserAuthServiceAdapter implements UserAuthPort {
    private final CommonWebClient commonWebClient;

    @Override
    public Mono<Boolean> checkAuthToChangeTeacher(Long adminUserId) {
        return Mono.just(true);
    }
}
