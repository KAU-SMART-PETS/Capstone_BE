package com.kau.capstone.domain.auth.util;

import com.kau.capstone.domain.auth.util.redirect.OAuthRedirectProvider;
import com.kau.capstone.domain.auth.util.redirect.OAuthRedirectProviders;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class AuthConfig {

    @Bean
    public OAuthRedirectProviders oAuthRedirectProviders(List<OAuthRedirectProvider> oAuthRedirectProviders) {
        return new OAuthRedirectProviders(oAuthRedirectProviders);
    }
}
