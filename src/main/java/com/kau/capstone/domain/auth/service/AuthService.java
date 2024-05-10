package com.kau.capstone.domain.auth.service;

import jakarta.servlet.http.Cookie;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private static final String COOKIE_NAME = "JSESSIONID";
    private static final int EXPIRE_TIME = 0;
    private static final String HOME_PATH = "/";

    public Cookie expireCookie() {
        Cookie cookie = new Cookie(COOKIE_NAME, null);
        cookie.setMaxAge(EXPIRE_TIME);
        cookie.setPath(HOME_PATH);

        return cookie;
    }

}
