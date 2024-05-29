package com.kau.capstone.domain.auth.util.provider;

import com.kau.capstone.domain.auth.dto.UserInfoDto;
import com.kau.capstone.domain.auth.util.SocialSite;
import com.kau.capstone.domain.auth.util.provider.access.KakaoAccessToken;
import com.kau.capstone.domain.auth.util.provider.redirect.KakaoRedirect;
import org.springframework.stereotype.Component;

@Component
public class KakaoProvider implements OAuthProvider {

    private final KakaoRedirect redirect;
    private final KakaoAccessToken accessToken;

    public KakaoProvider(KakaoRedirect redirect, KakaoAccessToken accessToken) {
        this.redirect = redirect;
        this.accessToken = accessToken;
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
    public SocialSite getSocialSite() {
        return SocialSite.KAKAO;
    }
}
