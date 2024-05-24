package com.kau.capstone.domain.auth.util;

import com.kau.capstone.domain.auth.dto.LoginInfo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Objects;

@Component
public class AuthArgumentResolver implements HandlerMethodArgumentResolver {

    private static final String LOGIN_SESSION_ATTRIBUTE_NAME = "memberId";

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(LoginUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        HttpSession session = Objects.requireNonNull(request).getSession(false);
        String memberId = String.valueOf(session.getAttribute(LOGIN_SESSION_ATTRIBUTE_NAME));

        return new LoginInfo(Long.valueOf(memberId));
    }
}
