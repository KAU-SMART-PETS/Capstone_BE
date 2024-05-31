package com.kau.capstone.domain.auth.util.provider;

import com.kau.capstone.domain.auth.dto.TokenInfo;
import com.kau.capstone.domain.auth.dto.UserInfoDto;
import com.kau.capstone.domain.auth.util.SocialSite;

public interface OAuthProvider {

    String getRedirectURL();

    UserInfoDto getUserInfo(String code);

    TokenInfo updateToken(String beforeRefreshToken);

    void logout(String refreshToken);

    SocialSite getSocialSite();
}
