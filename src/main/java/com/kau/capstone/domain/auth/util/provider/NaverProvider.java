package com.kau.capstone.domain.auth.util.provider;

import com.kau.capstone.domain.auth.dto.TokenInfo;
import com.kau.capstone.domain.auth.dto.UserInfoDto;
import com.kau.capstone.domain.auth.util.SocialSite;
import com.kau.capstone.domain.auth.util.provider.access.NaverAccessToken;
import com.kau.capstone.domain.auth.util.provider.logout.NaverLogout;
import com.kau.capstone.domain.auth.util.provider.redirect.NaverRedirect;
import org.springframework.stereotype.Component;

@Component
public class NaverProvider implements OAuthProvider {

    private final NaverRedirect redirect;
    private final NaverAccessToken accessToken;
    private final NaverLogout naverLogout;

    public NaverProvider(NaverRedirect redirect, NaverAccessToken accessToken, NaverLogout naverLogout) {
        this.redirect = redirect;
        this.accessToken = accessToken;
        this.naverLogout = naverLogout;
    }

    @Override
    public String getRedirectURL() {
        return redirect.getRedirectURL();
    }

    @Override
    public UserInfoDto getUserInfo(String code) {
        return accessToken.getPlatformUser(code);
    }

    @Override
    public TokenInfo updateToken(String beforeRefreshToken) {
        return TokenInfo.builder().build();
    }

    @Override
    public void logout(String refreshToken) {
        naverLogout.logout(refreshToken);
    }

    @Override
    public SocialSite getSocialSite() {
        return SocialSite.NAVER;
    }
}
