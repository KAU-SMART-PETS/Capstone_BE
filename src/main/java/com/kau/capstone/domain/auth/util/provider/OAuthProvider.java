package com.kau.capstone.domain.auth.util.provider;

import com.kau.capstone.domain.auth.dto.UserInfoDto;
import com.kau.capstone.domain.auth.util.SocialSite;

public interface OAuthProvider {

    String getRedirectURL();

    UserInfoDto getUserInfo(String code);

    SocialSite getSocialSite();
}
