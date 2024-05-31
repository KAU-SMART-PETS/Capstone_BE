package com.kau.capstone.domain.auth.util.provider;

import com.kau.capstone.domain.auth.dto.TokenResponse;
import com.kau.capstone.domain.auth.dto.UserInfoDto;
import com.kau.capstone.domain.auth.util.SocialSite;
import com.kau.capstone.domain.auth.util.provider.access.KakaoAccessToken;
import com.kau.capstone.domain.auth.util.provider.logout.KakaoLogout;
import com.kau.capstone.domain.auth.util.provider.redirect.KakaoRedirect;
import com.kau.capstone.domain.auth.util.provider.refresh.KakaoRefreshToken;
import org.springframework.stereotype.Component;

@Component
public class KakaoProvider implements OAuthProvider {

    private final KakaoRedirect redirect;
    private final KakaoAccessToken accessToken;
    private final KakaoRefreshToken refreshToken;
    private final KakaoLogout logout;

    public KakaoProvider(KakaoRedirect redirect, KakaoAccessToken accessToken, KakaoRefreshToken refreshToken,
                         KakaoLogout logout) {
        this.redirect = redirect;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.logout = logout;
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
    public TokenResponse updateToken(String beforeRefreshToken) {
        return refreshToken.updateToken(beforeRefreshToken);
    }

    @Override
    public void logout(String platformId) {
        logout.logout(platformId);
    }

    @Override
    public SocialSite getSocialSite() {
        return SocialSite.KAKAO;
    }
}
