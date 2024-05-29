package com.kau.capstone.domain.auth.service;

import com.kau.capstone.domain.auth.dto.SignUserDto;
import com.kau.capstone.domain.auth.dto.UserInfoDto;
import com.kau.capstone.domain.auth.util.SocialSite;
import com.kau.capstone.domain.auth.util.provider.OAuthProvider;
import com.kau.capstone.domain.auth.util.provider.OAuthProviders;
import jakarta.servlet.http.Cookie;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthService {

    private static final String COOKIE_NAME = "JSESSIONID";
    private static final int EXPIRE_TIME = 0;
    private static final String HOME_PATH = "/";

    private final OAuthProviders oAuthProviders;

    public AuthService(OAuthProviders oAuthProviders) {
        this.oAuthProviders = oAuthProviders;
    }

    public Cookie expireCookie() {
        Cookie cookie = new Cookie(COOKIE_NAME, null);
        cookie.setMaxAge(EXPIRE_TIME);
        cookie.setPath(HOME_PATH);

        return cookie;
    }

    public String getLoginRedirectURL(String site) {
        SocialSite socialSite = SocialSite.findBySocialSite(site);
        OAuthProvider oAuthProvider = oAuthProviders.getClient(socialSite);

        return oAuthProvider.getRedirectURL();
    }

    public SignUserDto loginAuthorizeUser(String site, String code) {
        SocialSite socialSite = SocialSite.findBySocialSite(site);
        OAuthProvider oAuthProvider = oAuthProviders.getClient(socialSite);
        UserInfoDto userInfoDto = oAuthProvider.getUserInfo(code);

        return SignUserDto.from(false, 1L);
    }
}
