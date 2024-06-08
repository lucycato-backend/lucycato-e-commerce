package org.lucycato.gatewayserver.filter;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.lucycato.webflux.CommonWebClient;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;

@Order(-10)
@Component
public class AuthGlobalFilter extends AbstractGatewayFilterFactory<AuthGlobalFilter.Config> {
    private final CommonWebClient commonWebClient;

    public AuthGlobalFilter(CommonWebClient commonWebClient) {
        super(Config.class);
        this.commonWebClient = commonWebClient;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            String token = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

            if (token != null && token.startsWith("Bearer ")) {
                //TODO: user-auth-service 통신 & 주입
            }
            return chain.filter(exchange);
        };
    }

    @Data
    public static class Config {
        private String message;

        private Boolean authCheck;
    }
}