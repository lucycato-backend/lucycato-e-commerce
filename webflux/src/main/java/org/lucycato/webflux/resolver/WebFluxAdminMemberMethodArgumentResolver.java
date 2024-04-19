package org.lucycato.webflux.resolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.lucycato.common.XHeaderContext;
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
        List<String> adminMemberJsonList = exchange.getRequest().getHeaders().get(XHeaderContext.ADMIN_OR_APP_MEMBER_JSON_STRING_HEADER_KEY);
        if (adminMemberJsonList != null && !adminMemberJsonList.isEmpty()) {
            return Mono.fromCallable(() -> objectMapper.readValue(adminMemberJsonList.get(0), AdminUserHeaderDetail.class))
                    .map(readValue -> new AdminUserHeaderDetail(
                            readValue.getAdminMemberId()
                    ));
        }
        return Mono.error(new ApiExceptionImpl(ErrorCodeImpl.RESOLVER_VALUE_NOT_FOUNT));
    }
}



