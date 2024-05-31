package com.kau.capstone.domain.auth.util.provider;

import com.kau.capstone.domain.auth.dto.TokenResponse;
import com.kau.capstone.domain.auth.dto.UserInfoDto;
import com.kau.capstone.domain.auth.util.SocialSite;

public interface OAuthProvider {

    String getRedirectURL();

    UserInfoDto getUserInfo(String code);

    TokenResponse updateToken(String beforeRefreshToken);

    void logout(String platformId);

    SocialSite getSocialSite();
}
