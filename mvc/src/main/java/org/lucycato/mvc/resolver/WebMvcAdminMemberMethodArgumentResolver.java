package org.lucycato.mvc.resolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.lucycato.common.XHeaderContext;
import org.lucycato.common.annotation.resolver.AdminMemberHeaders;
import org.lucycato.common.error.ErrorCodeImpl;
import org.lucycato.common.exception.ApiExceptionImpl;
import org.lucycato.common.resolver.AdminMemberHeaderDetail;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
@RequiredArgsConstructor
public class WebMvcAdminMemberMethodArgumentResolver implements HandlerMethodArgumentResolver {
    private final ObjectMapper objectMapper;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        Boolean annotation = parameter.hasParameterAnnotation(AdminMemberHeaders.class);
        Boolean parameterType = parameter.getParameterType().equals(AdminMemberHeaderDetail.class);
        return annotation && parameterType;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String[] adminMemberJsonList = webRequest.getHeaderValues(XHeaderContext.ADMIN_OR_APP_MEMBER_JSON_STRING_HEADER_KEY);
        if (adminMemberJsonList != null && adminMemberJsonList.length != 0) {
            AdminMemberHeaderDetail readValue = objectMapper.readValue(adminMemberJsonList[0], AdminMemberHeaderDetail.class);
            return new AdminMemberHeaderDetail(
                    readValue.getAdminMemberId()
            );
        }
        return new ApiExceptionImpl(ErrorCodeImpl.RESOLVER_VALUE_NOT_FOUNT);
    }
}
