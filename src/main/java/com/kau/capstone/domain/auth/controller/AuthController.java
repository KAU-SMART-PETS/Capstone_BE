package com.kau.capstone.domain.auth.controller;

import com.kau.capstone.domain.auth.dto.LoginInfo;
import com.kau.capstone.domain.auth.dto.SignUserDto;
import com.kau.capstone.domain.auth.service.AuthService;
import com.kau.capstone.domain.auth.util.LoginUser;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URI;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private static final String LOGIN_ATTRIBUTE_NAME = "memberId";
    private static final String HOME = "/";
    private static final String MYPAGE = "/mypage";

    private final AuthService authService;

    @PostMapping("/api/v1/login/{memberId}")
    public ResponseEntity<Void> login(@PathVariable Long memberId,
                                      HttpServletRequest request) {
        request.getSession().setAttribute(LOGIN_ATTRIBUTE_NAME, memberId);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/v1/oauth2/{site}")
    public ResponseEntity<Void> oauthLogin(@PathVariable String site) {
        String redirectURL = authService.getLoginRedirectURL(site);

        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(redirectURL))
                .build();
    }

    @PostMapping("/api/v1/oauth2/{site}/code")
    public ResponseEntity<Void> oauthLoginCode(@PathVariable String site,
                                               @RequestParam("code") String code,
                                               HttpServletRequest request) {
        SignUserDto signUserDto = authService.loginAuthorizeUser(site, code);
        request.getSession().setAttribute(LOGIN_ATTRIBUTE_NAME, signUserDto.memberId());

        if (signUserDto.isSignUp()) {
            ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY)
                    .location(URI.create(MYPAGE))
                    .build();
        }
        return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY)
                .location(URI.create(HOME))
                .build();
    }

    @PostMapping("/api/v1/oauth2/logout")
    public ResponseEntity<Void> oauthLogout(@LoginUser LoginInfo loginInfo,
                                            HttpServletRequest request, HttpServletResponse response) {
        authService.logout(loginInfo.memberId());

        request.getSession().removeAttribute(LOGIN_ATTRIBUTE_NAME);

        Cookie cookie = authService.expireCookie();
        response.addCookie(cookie);

        return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY)
                .location(URI.create(HOME))
                .build();
    }

    @PostMapping("/api/v1/logout")
    public ResponseEntity<Void> logout(@LoginUser LoginInfo loginInfo,
                                       HttpServletRequest request, HttpServletResponse response) {
        request.getSession().removeAttribute(LOGIN_ATTRIBUTE_NAME);

        Cookie cookie = authService.expireCookie();
        response.addCookie(cookie);

        return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY)
                .location(URI.create(HOME))
                .build();
    }
}
