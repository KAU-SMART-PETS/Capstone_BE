package com.kau.capstone.domain.auth.util;

import com.kau.capstone.domain.auth.util.provider.OAuthProvider;
import com.kau.capstone.domain.auth.util.provider.OAuthProviders;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class AuthConfig {

    @Bean
    public OAuthProviders oAuthRedirectProviders(List<OAuthProvider> oAuthProviders) {
        return new OAuthProviders(oAuthProviders);
    }
}
