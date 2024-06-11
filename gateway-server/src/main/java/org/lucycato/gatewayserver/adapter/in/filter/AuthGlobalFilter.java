package org.lucycato.gatewayserver.adapter.in.filter;

import lombok.Data;
import org.lucycato.common.context.XHeaderContext;
import org.lucycato.common.exception.ApiExceptionImpl;
import org.lucycato.gatewayserver.application.port.in.AuthGlobalFilterUseCase;
import org.lucycato.gatewayserver.error.GatewayErrorCodeImpl;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Order(-10)
@Component
public class AuthGlobalFilter extends AbstractGatewayFilterFactory<AuthGlobalFilter.Config> {
    private final String WHITELIST_OPEN_API_KEY = "open-api";
    private final String WHITELIST_API_KEY = "api";
    private final String AUTH_ADMIN_KEY = "admin";
    private final String AUTH_APP_KEY = "app";

    private final AuthGlobalFilterUseCase authGlobalFilterUseCase;

    public AuthGlobalFilter(AuthGlobalFilterUseCase authGlobalFilterUseCase) {
        super(Config.class);
        this.authGlobalFilterUseCase = authGlobalFilterUseCase;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> Mono.just(exchange.getRequest().getURI().getPath())
                .map(uri -> uri.substring(1).split("/")).flatMap(split -> {
                    String whitelist = split[1];
                    if (whitelist.equals(WHITELIST_OPEN_API_KEY)) {
                        return Mono.just("");
                    } else if (whitelist.equals(WHITELIST_API_KEY)) {
                        ServerHttpRequest request = exchange.getRequest();
                        String auth = split[2];
                        if (auth.equals(AUTH_ADMIN_KEY)) {
                            String token = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
                            return authGlobalFilterUseCase.parseJwtToAdminUserJson(token);
                        } else if (auth.equals(AUTH_APP_KEY)) {
                            String token = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
                            return authGlobalFilterUseCase.parseJwtToAppUserJson(token);
                        }
                    }
                    return Mono.error(new ApiExceptionImpl(GatewayErrorCodeImpl.INVALID_URI));
                })
                .onErrorResume(error -> Mono.error(new ApiExceptionImpl(GatewayErrorCodeImpl.INVALID_URI, error)))
                .switchIfEmpty(Mono.error(new ApiExceptionImpl(GatewayErrorCodeImpl.INVALID_TOKEN)))
                .flatMap(authJson -> chain.filter(exchange.mutate().request(exchange.getRequest().mutate().headers(httpHeaders -> {
                    if (!authJson.isEmpty()) {
                        httpHeaders.set(XHeaderContext.ADMIN_OR_APP_USER_JSON_STRING_HEADER_KEY, authJson);
                    }
                }).build()).build()));
    }

    @Data
    public static class Config {
        private String message;
    }
}