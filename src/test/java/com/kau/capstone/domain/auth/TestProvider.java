package com.kau.capstone.domain.auth;

import com.kau.capstone.domain.auth.access.TestAccessToken;
import com.kau.capstone.v1.auth.dto.TokenInfo;
import com.kau.capstone.v1.auth.dto.UserInfoDto;
import com.kau.capstone.domain.auth.logout.TestLogout;
import com.kau.capstone.domain.auth.redirect.TestRedirect;
import com.kau.capstone.domain.auth.refresh.TestRefreshToken;
import com.kau.capstone.v1.auth.util.SocialSite;
import com.kau.capstone.v1.auth.util.provider.OAuthProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("test")
@RequiredArgsConstructor
public class TestProvider implements OAuthProvider {

    private final TestRedirect redirect;
    private final TestAccessToken accessToken;
    private final TestRefreshToken refreshToken;
    private final TestLogout logout;

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
