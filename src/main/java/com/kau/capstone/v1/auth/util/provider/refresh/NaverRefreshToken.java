package com.kau.capstone.v1.auth.util.provider.refresh;

import com.kau.capstone.v1.auth.dto.NaverAccessTokenResponse;
import com.kau.capstone.v1.auth.dto.TokenInfo;
import com.kau.capstone.v1.auth.util.provider.secret.NaverSecretValue;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class NaverRefreshToken {

    private static final String UPDATE_TOKEN_URL = "https://nid.naver.com/oauth2.0/token";
    public static final String GRANT_TYPE = "grant_type";
    public static final String REFRESH_TOKEN = "refresh_token";
    public static final String CLIENT_ID = "client_id";
    public static final String CLIENT_SECRET = "client_secret";

    private final RestTemplate restTemplate;
    private final NaverSecretValue secretValue;

    public NaverRefreshToken(RestTemplateBuilder restTemplateBuilder, NaverSecretValue secretValue) {
        this.restTemplate = restTemplateBuilder.build();
        this.secretValue = secretValue;
    }

    public TokenInfo updateToken(String refreshToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(GRANT_TYPE, REFRESH_TOKEN);
        headers.set(CLIENT_ID, secretValue.getClientId());
        headers.set(CLIENT_SECRET, secretValue.getClientSecret());
        headers.set(REFRESH_TOKEN, refreshToken);

        NaverAccessTokenResponse response = restTemplate.postForEntity(UPDATE_TOKEN_URL, headers,
                NaverAccessTokenResponse.class).getBody();

        return TokenInfo.toResponse(response);
    }
}
