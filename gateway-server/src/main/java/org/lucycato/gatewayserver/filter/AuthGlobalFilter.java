package org.lucycato.gatewayserver.filter;

import lombok.Data;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(Integer.MIN_VALUE)
@Component
public class AuthGlobalFilter extends AbstractGatewayFilterFactory<AuthGlobalFilter.Config> {

    public AuthGlobalFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            System.out.println(config.message);

            System.out.println(config.authCheck);

            return chain.filter(exchange);
        };
    }

    @Data
    public static class Config {
        private String message;

        private Boolean authCheck;
    }
}
