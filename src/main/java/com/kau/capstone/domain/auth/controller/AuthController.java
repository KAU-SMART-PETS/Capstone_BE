package com.kau.capstone.domain.auth.controller;

import com.kau.capstone.domain.auth.service.AuthService;
import com.kau.capstone.domain.auth.util.LoginUser;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    private static final String LOGIN_ATTRIBUTE_NAME = "memberId";

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/api/v1/login/{memberId}")
    public ResponseEntity<Void> login(@PathVariable Long memberId,
                                      HttpServletRequest request) {
        request.getSession().setAttribute(LOGIN_ATTRIBUTE_NAME, memberId);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/api/v1/logout")
    public ResponseEntity<Void> logout(@LoginUser Long memberId,
                                       HttpServletRequest request, HttpServletResponse response) {
        request.getSession().removeAttribute(LOGIN_ATTRIBUTE_NAME);

        Cookie cookie = authService.expireCookie();
        response.addCookie(cookie);

        return ResponseEntity.ok().build();
    }
}
