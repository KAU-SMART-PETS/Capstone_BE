package com.kau.capstone.domain.auth.util.provider;

import com.kau.capstone.domain.auth.exception.AuthException;
import com.kau.capstone.domain.auth.util.SocialSite;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.kau.capstone.global.exception.ErrorCode.SOCIAL_SITE_NOT_SUPPORTED;

public class OAuthProviders {

    private final Map<SocialSite, OAuthProvider> oAuthRedirectProviderMap = new EnumMap<>(SocialSite.class);

    public OAuthProviders(List<OAuthProvider> oAuthProviders) {
        for (OAuthProvider oAuthProvider : oAuthProviders) {
            SocialSite socialSite = oAuthProvider.getSocialSite();
            oAuthRedirectProviderMap.put(socialSite, oAuthProvider);
        }
    }

    public OAuthProvider getClient(SocialSite site) {
        return Optional.ofNullable(oAuthRedirectProviderMap.get(site))
                .orElseThrow(() -> new AuthException(SOCIAL_SITE_NOT_SUPPORTED));
    }
}
