package org.lucycato.webflux.filter;

import org.lucycato.common.XHeaderContext;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.List;

public class WebfluxFilter implements WebFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        List<String> adminOrAppMemberJsonStringList = exchange.getRequest().getHeaders().get(XHeaderContext.ADMIN_OR_APP_MEMBER_JSON_STRING_HEADER_KEY);
        if (adminOrAppMemberJsonStringList != null && !adminOrAppMemberJsonStringList.isEmpty()) {
            return Mono.just(adminOrAppMemberJsonStringList.get(0))
                    .flatMap(adminOrAppMemberJsonString ->
                            chain.filter(exchange)
                                    .contextWrite(context -> context.put(XHeaderContext.ADMIN_OR_APP_MEMBER_JSON_STRING_HEADER_KEY, adminOrAppMemberJsonString))
                    );
        }
        return chain.filter(exchange);
    }
}
