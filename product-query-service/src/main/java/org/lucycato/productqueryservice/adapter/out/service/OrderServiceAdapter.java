package org.lucycato.productqueryservice.adapter.out.service;

import lombok.RequiredArgsConstructor;
import org.lucycato.common.annotation.hexagonal.out.ServiceAdapter;
import org.lucycato.productqueryservice.application.port.out.OrderPort;
import reactor.core.publisher.Mono;

@ServiceAdapter
@RequiredArgsConstructor
public class OrderServiceAdapter implements OrderPort {

    @Override
    public Mono<Boolean> checkAppUserBuyCourse(Long appUserId, Long courseId) {
        return null;
    }
}
