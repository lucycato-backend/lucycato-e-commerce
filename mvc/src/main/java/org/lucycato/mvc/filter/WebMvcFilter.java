package org.lucycato.mvc.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.lucycato.common.XHeaderContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.io.IOException;
import java.util.Objects;

@Order(Integer.MIN_VALUE)
@Component
public class WebMvcFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (servletRequest instanceof HttpServletRequest httpServletRequest) {
            String adminOrAppMemberJsonString = httpServletRequest.getHeader(XHeaderContext.ADMIN_OR_APP_MEMBER_JSON_STRING_HEADER_KEY);
            if (adminOrAppMemberJsonString != null) {
                RequestAttributes requestAttributes = Objects.requireNonNull(RequestContextHolder.getRequestAttributes());
                requestAttributes.setAttribute(XHeaderContext.ADMIN_OR_APP_MEMBER_JSON_STRING_HEADER_KEY, adminOrAppMemberJsonString, RequestAttributes.SCOPE_REQUEST);

                filterChain.doFilter(servletRequest, servletResponse);
            }
        }
    }
}
