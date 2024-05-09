package com.kau.capstone.domain.auth.service;

import jakarta.servlet.http.Cookie;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private static final String COOKIE_NAME = "JSESSIONID";

    public Cookie expireCookie() {
        Cookie cookie = new Cookie(COOKIE_NAME, null);
        cookie.setMaxAge(0);
        cookie.setPath("/");

        return cookie;
    }

}
