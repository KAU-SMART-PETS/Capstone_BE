package com.kau.capstone.v2.pet.util;

import com.kau.capstone.global.exception.ErrorCode;
import com.kau.capstone.v1.auth.exception.AuthException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Objects;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

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
