package com.kau.capstone.v1.auth.util.provider.refresh;

import com.kau.capstone.v1.auth.dto.KakaoAccessTokenResponse;
import com.kau.capstone.v1.auth.dto.TokenInfo;
import com.kau.capstone.v1.auth.util.provider.secret.KakaoSecretValue;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class KakaoRefreshToken {

    private static final String UPDATE_TOKEN_URL = "https://kauth.kakao.com/oauth/token";
    public static final String GRANT_TYPE = "grant_type";
    public static final String REFRESH_TOKEN = "refresh_token";
    public static final String CLIENT_ID = "client_id";

    private final RestTemplate restTemplate;
    private final KakaoSecretValue secretValue;

    public KakaoRefreshToken(RestTemplateBuilder restTemplateBuilder, KakaoSecretValue secretValue) {
        this.restTemplate = restTemplateBuilder.build();
        this.secretValue = secretValue;
    }

    public TokenInfo updateToken(String refreshToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set(GRANT_TYPE, REFRESH_TOKEN);
        headers.set(CLIENT_ID, secretValue.getRestApiKey());
        headers.set(REFRESH_TOKEN, refreshToken);

        KakaoAccessTokenResponse response = restTemplate.postForEntity(UPDATE_TOKEN_URL, headers,
                KakaoAccessTokenResponse.class).getBody();

        return TokenInfo.toResponse(response);
    }
}
