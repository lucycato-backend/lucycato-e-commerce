package org.lucycato.mvc.resolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.lucycato.common.annotation.resolver.AppUserHeaders;
import org.lucycato.common.context.XHeaderContext;
import org.lucycato.common.error.ErrorCodeImpl;
import org.lucycato.common.exception.ApiExceptionImpl;
import org.lucycato.common.resolver.AppUserHeaderDetail;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
@RequiredArgsConstructor
public class WebMvcAppMemberMethodArgumentResolver implements HandlerMethodArgumentResolver {
    private final ObjectMapper objectMapper;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        Boolean annotation = parameter.hasParameterAnnotation(AppUserHeaders.class);
        Boolean parameterType = parameter.getParameterType().equals(AppUserHeaderDetail.class);
        return annotation && parameterType;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String[] appUserJsonList = webRequest.getHeaderValues(XHeaderContext.ADMIN_OR_APP_USER_JSON_STRING_HEADER_KEY);
        if (appUserJsonList != null && appUserJsonList.length != 0){
            AppUserHeaderDetail appUserHeaderDetail = objectMapper.readValue(appUserJsonList[0], AppUserHeaderDetail.class);
            if (appUserHeaderDetail.getAppMemberId() != null) {
                return appUserHeaderDetail;
            }
        }
        return new ApiExceptionImpl(ErrorCodeImpl.RESOLVER_VALUE_NOT_FOUNT);
    }
}
