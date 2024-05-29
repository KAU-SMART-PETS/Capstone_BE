package com.kau.capstone.domain.auth.util.provider.secret;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class KakaoSecretValue {

    @Value("${kakao.rest-api-key}")
    private String restApiKey;

    @Value("${kakao.redirect-uri}")
    private String redirectUri;

    @Value("${kakao.admin-key}")
    private String adminKey;
}
