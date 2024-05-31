package com.kau.capstone.domain.auth.util.provider.secret;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class KakaoSecretValue {

    private final String restApiKey;
    private final String redirectUri;
    private final String adminKey;

    public KakaoSecretValue(
            @Value("${kakao.rest-api-key}") String restApiKey,
            @Value("${kakao.redirect-uri}") String redirectUri,
            @Value("${kakao.admin-key}") String adminKey) {
        this.restApiKey = restApiKey;
        this.redirectUri = redirectUri;
        this.adminKey = adminKey;
    }
}
