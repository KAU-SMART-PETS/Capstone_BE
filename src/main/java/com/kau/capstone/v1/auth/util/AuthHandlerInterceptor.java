package com.kau.capstone.v1.auth.util;

import com.kau.capstone.v1.auth.exception.AuthException;
import com.kau.capstone.global.exception.ErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Objects;

@Component
public class AuthHandlerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        HttpSession session = request.getSession(false);

        if (Objects.isNull(session)) {
            throw new AuthException(ErrorCode.LOGIN_MEMBER_NOT_FOUND);
        }

        return true;
    }
}
