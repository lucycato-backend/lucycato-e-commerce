package org.lucycato.gatewayserver.application.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.lucycato.gatewayserver.application.port.in.AuthGlobalFilterUseCase;
import org.lucycato.gatewayserver.application.port.out.UserAuthPort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AuthGlobalFilterService implements AuthGlobalFilterUseCase {

    private final ObjectMapper objectMapper;
    private final UserAuthPort userAuthPort;

    @Override
    public Mono<String> parseJwtToAdminUserJson(String token) {
        if (token != null && token.startsWith("Bearer ")) {
            return userAuthPort.getAdminUserAuthByToken(token)
                    .flatMap(adminUserAuth -> Mono.fromCallable(() -> objectMapper.writeValueAsString(adminUserAuth)));
        } else {
            return Mono.empty();
        }
    }

    @Override
    public Mono<String> parseJwtToAppUserJson(String token) {
        if (token != null && token.startsWith("Bearer ")) {
            return userAuthPort.getAppUserAuthByToken(token)
                    .flatMap(appUserAuth -> Mono.fromCallable(() -> objectMapper.writeValueAsString(appUserAuth)));
        } else {
            return Mono.empty();
        }
    }
}