package org.lucycato.mvc.resolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.lucycato.common.XHeaderContext;
import org.lucycato.common.annotation.resolver.AppUserHeaders;
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
        String[] appMemberJsonList = webRequest.getHeaderValues(XHeaderContext.ADMIN_OR_APP_MEMBER_JSON_STRING_HEADER_KEY);
        if (appMemberJsonList != null && appMemberJsonList.length != 0){
            AppUserHeaderDetail readValue = objectMapper.readValue(appMemberJsonList[0], AppUserHeaderDetail.class);
            return new AppUserHeaderDetail(
                    readValue.getAppMemberId()
            );
        }
        return new ApiExceptionImpl(ErrorCodeImpl.RESOLVER_VALUE_NOT_FOUNT);
    }
}
