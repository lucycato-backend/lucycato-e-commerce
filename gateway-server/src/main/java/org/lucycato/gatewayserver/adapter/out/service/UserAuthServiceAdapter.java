package org.lucycato.gatewayserver.adapter.out.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.lucycato.common.annotation.hexagonal.out.ServiceAdapter;
import org.lucycato.common.resolver.AdminUserHeaderDetail;
import org.lucycato.common.resolver.AppUserHeaderDetail;
import org.lucycato.gatewayserver.application.port.out.UserAuthPort;
import reactor.core.publisher.Mono;

@ServiceAdapter
@RequiredArgsConstructor
public class UserAuthServiceAdapter implements UserAuthPort {
    private final ObjectMapper objectMapper;

    @Override
    public Mono<AdminUserHeaderDetail> getAdminUserAuthByToken(String token) {
        return Mono.just(new AdminUserHeaderDetail(1L));
    }

    @Override
    public Mono<AppUserHeaderDetail> getAppUserAuthByToken(String token) {
        return Mono.just(new AppUserHeaderDetail(1L));
    }
}
