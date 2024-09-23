package com.kau.capstone.common;

import com.kau.capstone.domain.auth.dto.TokenInfo;
import com.kau.capstone.domain.auth.dto.UserInfoDto;
import com.kau.capstone.domain.auth.util.SocialSite;
import com.kau.capstone.domain.auth.util.provider.OAuthProvider;
import com.kau.capstone.domain.auth.util.provider.access.KakaoAccessToken;
import com.kau.capstone.domain.auth.util.provider.logout.KakaoLogout;
import com.kau.capstone.domain.auth.util.provider.redirect.KakaoRedirect;
import com.kau.capstone.domain.auth.util.provider.refresh.KakaoRefreshToken;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("test")
public class TestProvider implements OAuthProvider {

    private final KakaoRedirect redirect;
    private final KakaoAccessToken accessToken;
    private final KakaoRefreshToken refreshToken;
    private final KakaoLogout logout;

    public TestProvider(KakaoRedirect redirect, KakaoAccessToken accessToken, KakaoRefreshToken refreshToken, KakaoLogout logout) {
        this.redirect = redirect;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.logout = logout;
    }

    @Override
    public String getRedirectURL() {
        return null;
    }

    @Override
    public UserInfoDto getUserInfo(String code) {
        return UserInfoDto.builder()
                .id(code)
                .nickname("test")
                .email("test@test.com")
                .build();
    }

    @Override
    public TokenInfo updateToken(String beforeRefreshToken) {
        return null;
    }

    @Override
    public void logout(String refreshToken) {

    }

    @Override
    public SocialSite getSocialSite() {
        return SocialSite.TEST;
    }
}
