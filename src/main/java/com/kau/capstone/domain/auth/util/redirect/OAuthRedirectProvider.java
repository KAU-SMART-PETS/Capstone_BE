package com.kau.capstone.domain.auth.util.redirect;

import com.kau.capstone.domain.auth.util.SocialSite;

public interface OAuthRedirectProvider {

    String getRedirectURL();

    SocialSite getSocialSite();
}
