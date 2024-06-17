package org.lucycato.productqueryservice.adapter.out.service;

import lombok.RequiredArgsConstructor;
import org.lucycato.common.annotation.hexagonal.out.ServiceAdapter;
import org.lucycato.productqueryservice.application.port.out.OrderPort;
import org.lucycato.webflux.CommonWebClient;
import reactor.core.publisher.Mono;

@ServiceAdapter
@RequiredArgsConstructor
public class OrderServiceAdapter implements OrderPort {
    private final CommonWebClient commonWebClient;

    @Override
    public Mono<Boolean> checkAppUserBuyCourse(Long appUserId, Long courseId) {
        return Mono.just(true);
    }
}
