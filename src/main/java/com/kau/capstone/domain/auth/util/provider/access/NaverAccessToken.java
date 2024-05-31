package com.kau.capstone.domain.auth.util.provider.access;

import com.kau.capstone.domain.auth.dto.NaverAccessTokenResponse;
import com.kau.capstone.domain.auth.dto.NaverUserInfoResponse;
import com.kau.capstone.domain.auth.dto.TokenInfo;
import com.kau.capstone.domain.auth.dto.UserInfoDto;
import com.kau.capstone.domain.auth.util.provider.secret.NaverSecretValue;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class NaverAccessToken {

    private static final String ACCESS_TOKEN_URL = "https://nid.naver.com/oauth2.0/token";
    private static final String USER_INFO_URL = "https://openapi.naver.com/v1/nid/me";

    private static final String AUTHORIZATION_CODE = "authorization_code";
    private static final String GRANT_TYPE = "grant_type";
    private static final String CLIENT_ID = "client_id";
    public static final String CLIENT_SECRET = "client_secret";
    private static final String REDIRECT_URI = "redirect_uri";
    private static final String CODE = "code";

    private final RestTemplate restTemplate;
    private final NaverSecretValue secretValue;

    public NaverAccessToken(RestTemplateBuilder restTemplateBuilder, NaverSecretValue secretValue) {
        this.restTemplate = restTemplateBuilder.build();
        this.secretValue = secretValue;
    }

    public UserInfoDto getPlatformUser(String code) {
        TokenInfo tokenInfo = requestAccessToken(code);
        return getUserInfo(tokenInfo);
    }

    private TokenInfo requestAccessToken(String code) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(GRANT_TYPE, AUTHORIZATION_CODE);
        headers.set(CLIENT_ID, secretValue.getClientId());
        headers.set(CLIENT_SECRET, secretValue.getClientSecret());
        headers.set(REDIRECT_URI, secretValue.getRedirectUri());
        headers.set(CODE, code);

        NaverAccessTokenResponse response = restTemplate.postForEntity(ACCESS_TOKEN_URL, headers,
                NaverAccessTokenResponse.class).getBody();

        return TokenInfo.toResponse(response);
    }

    private UserInfoDto getUserInfo(TokenInfo tokenInfo) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(tokenInfo.accessToken());

        NaverUserInfoResponse response = restTemplate.postForEntity(USER_INFO_URL, new HttpEntity<>(headers),
                NaverUserInfoResponse.class).getBody();

        return UserInfoDto.toDto(response, tokenInfo.refreshToken());
    }
}
