package org.lucycato.webflux.resolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.lucycato.common.context.XHeaderContext;
import org.lucycato.common.annotation.resolver.AdminUserHeaders;
import org.lucycato.common.error.ErrorCodeImpl;
import org.lucycato.common.exception.ApiExceptionImpl;
import org.lucycato.common.resolver.AdminUserHeaderDetail;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.BindingContext;
import org.springframework.web.reactive.result.method.HandlerMethodArgumentResolver;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@RequiredArgsConstructor
public class WebFluxAdminMemberMethodArgumentResolver implements HandlerMethodArgumentResolver {
    private final ObjectMapper objectMapper;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        Boolean annotation = parameter.hasParameterAnnotation(AdminUserHeaders.class);
        Boolean parameterType = parameter.getParameterType().equals(AdminUserHeaderDetail.class);
        return annotation && parameterType;
    }

    @Override
    public Mono<Object> resolveArgument(MethodParameter parameter, BindingContext bindingContext, ServerWebExchange exchange) {
        List<String> adminUserJsonList = exchange.getRequest().getHeaders().get(XHeaderContext.ADMIN_OR_APP_USER_JSON_STRING_HEADER_KEY);
        if (adminUserJsonList != null && !adminUserJsonList.isEmpty()) {
            return Mono.fromCallable(() -> objectMapper.readValue(adminUserJsonList.get(0), AdminUserHeaderDetail.class))
                    .filter(adminUserHeaderDetail -> adminUserHeaderDetail.getAdminUserId() != null)
                    .switchIfEmpty(Mono.error(new ApiExceptionImpl(ErrorCodeImpl.RESOLVER_VALUE_NOT_FOUNT)))
                    .map(adminUserHeaderDetail -> adminUserHeaderDetail);
        }
        return Mono.error(new ApiExceptionImpl(ErrorCodeImpl.RESOLVER_VALUE_NOT_FOUNT));
    }
}



