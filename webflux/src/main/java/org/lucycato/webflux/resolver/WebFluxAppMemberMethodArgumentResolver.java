package org.lucycato.webflux.resolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.lucycato.common.XHeaderContext;
import org.lucycato.common.annotation.resolver.AppMemberHeaders;
import org.lucycato.common.error.ErrorCodeImpl;
import org.lucycato.common.exception.ApiExceptionImpl;
import org.lucycato.common.resolver.AppMemberHeaderDetail;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.BindingContext;
import org.springframework.web.reactive.result.method.HandlerMethodArgumentResolver;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@RequiredArgsConstructor
public class WebFluxAppMemberMethodArgumentResolver implements HandlerMethodArgumentResolver {
    private final ObjectMapper objectMapper;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        Boolean annotation = parameter.hasParameterAnnotation(AppMemberHeaders.class);
        Boolean parameterType = parameter.getParameterType().equals(AppMemberHeaderDetail.class);
        return annotation && parameterType;
    }

    @Override
    public Mono<Object> resolveArgument(MethodParameter parameter, BindingContext bindingContext, ServerWebExchange exchange) {
        List<String> appMemberJsonList = exchange.getRequest().getHeaders().get(XHeaderContext.ADMIN_OR_APP_MEMBER_JSON_STRING_HEADER_KEY);
        if (appMemberJsonList != null && !appMemberJsonList.isEmpty()) {
            return Mono.fromCallable(() -> objectMapper.readValue(appMemberJsonList.get(0), AppMemberHeaderDetail.class))
                    .map(readValue -> new AppMemberHeaderDetail(
                        readValue.getAppMemberId()
                    ));
        }
        return Mono.error(new ApiExceptionImpl(ErrorCodeImpl.RESOLVER_VALUE_NOT_FOUNT));
    }
}
