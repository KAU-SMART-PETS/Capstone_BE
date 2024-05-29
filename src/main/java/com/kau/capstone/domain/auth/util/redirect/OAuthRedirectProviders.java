package com.kau.capstone.domain.auth.util.redirect;

import com.kau.capstone.domain.auth.util.SocialSite;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class OAuthRedirectProviders {

    private final Map<SocialSite, OAuthRedirectProvider> oAuthRedirectProviderMap = new EnumMap<>(SocialSite.class);

    public OAuthRedirectProviders(List<OAuthRedirectProvider> oAuthRedirectProviders) {
        for(OAuthRedirectProvider oAuthRedirectProvider: oAuthRedirectProviders) {
           SocialSite socialSite = oAuthRedirectProvider.getSocialSite();
           oAuthRedirectProviderMap.put(socialSite, oAuthRedirectProvider);
        }
    }

    public OAuthRedirectProvider getClient(SocialSite site) {
        return Optional.ofNullable(oAuthRedirectProviderMap.get(site))
                .orElseThrow(() -> new RuntimeException("지원하지 않는 소셜 로그인 사이트입니다"));
    }
}
