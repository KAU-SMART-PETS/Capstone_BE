package com.kau.capstone.v1.auth.util.provider;

import com.kau.capstone.v1.auth.dto.TokenInfo;
import com.kau.capstone.v1.auth.dto.UserInfoDto;
import com.kau.capstone.v1.auth.util.SocialSite;

public interface OAuthProvider {

    String getRedirectURL();

    UserInfoDto getUserInfo(String code);

    TokenInfo updateToken(String beforeRefreshToken);

    void logout(String refreshToken);

    SocialSite getSocialSite();
}
