package com.kau.capstone.domain.pet.util;

import com.kau.capstone.domain.auth.exception.AuthException;
import com.kau.capstone.global.exception.ErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Objects;

@Configuration
public class PetHandlerInterceptor implements HandlerInterceptor {

    private static final String GET = "GET";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (GET.equals(request.getMethod())) {
            return true;
        }

        final HttpSession session = request.getSession(false);

        if (Objects.isNull(session)) {
            throw new AuthException(ErrorCode.LOGIN_MEMBER_NOT_FOUND);
        }

        return true;
    }
}
