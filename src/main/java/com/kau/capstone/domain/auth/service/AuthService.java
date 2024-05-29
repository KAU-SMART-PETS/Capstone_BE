package com.kau.capstone.domain.auth.service;

import com.kau.capstone.domain.auth.util.SocialSite;
import com.kau.capstone.domain.auth.util.redirect.OAuthRedirectProvider;
import com.kau.capstone.domain.auth.util.redirect.OAuthRedirectProviders;
import jakarta.servlet.http.Cookie;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private static final String COOKIE_NAME = "JSESSIONID";
    private static final int EXPIRE_TIME = 0;
    private static final String HOME_PATH = "/";

    private final OAuthRedirectProviders oAuthRedirectProviders;

    public AuthService(OAuthRedirectProviders oAuthRedirectProviders) {
        this.oAuthRedirectProviders = oAuthRedirectProviders;
    }

    public Cookie expireCookie() {
        Cookie cookie = new Cookie(COOKIE_NAME, null);
        cookie.setMaxAge(EXPIRE_TIME);
        cookie.setPath(HOME_PATH);

        return cookie;
    }

    public String getLoginRedirectURL(String site) {
        SocialSite socialSite = SocialSite.findBySocialSite(site);
        OAuthRedirectProvider oAuthRedirectProvider = oAuthRedirectProviders.getClient(socialSite);

        return oAuthRedirectProvider.getRedirectURL();
    }

}
