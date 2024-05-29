package com.kau.capstone.domain.auth.util.provider.access;

import com.kau.capstone.domain.auth.dto.KakaoAccessTokenResponse;
import com.kau.capstone.domain.auth.dto.KakaoUserInfoResponse;
import com.kau.capstone.domain.auth.dto.UserInfoDto;
import com.kau.capstone.domain.auth.util.provider.secret.KakaoSecretValue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
public class KakaoAccessToken {

    private static final String ACCESS_TOKEN_URL = "https://kauth.kakao.com/oauth/token";
    private static final String USER_INFO_URL = "https://kapi.kakao.com/v2/user/me";

    private static final String AUTHORIZATION_CODE = "authorization_code";
    private static final String GRANT_TYPE = "grant_type";
    private static final String CLIENT_ID = "client_id";
    private static final String REDIRECT_URI = "redirect_uri";
    private static final String CODE = "code";

    private final RestTemplate restTemplate;
    private final KakaoSecretValue secretValue;

    public KakaoAccessToken(RestTemplateBuilder restTemplateBuilder,
                            KakaoSecretValue secretValue) {
        this.restTemplate = restTemplateBuilder.build();
        this.secretValue = secretValue;
    }

    public UserInfoDto getPlatformUser(String code) {
        String accessToken = requestAccessToken(code);
        return getUserInfo(accessToken);
    }

    private String requestAccessToken(String code) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set(GRANT_TYPE, AUTHORIZATION_CODE);
        headers.set(CLIENT_ID, secretValue.getRestApiKey());
        headers.set(REDIRECT_URI, secretValue.getRedirectUri());
        headers.set(CODE, code);

        KakaoAccessTokenResponse response = restTemplate.postForEntity(ACCESS_TOKEN_URL, headers,
                KakaoAccessTokenResponse.class).getBody();

        return response.accessToken();
    }

    private UserInfoDto getUserInfo(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setBearerAuth(accessToken);

        KakaoUserInfoResponse response = restTemplate.postForEntity(USER_INFO_URL, new HttpEntity<>(headers),
                KakaoUserInfoResponse.class).getBody();

        return response.toUserInfo();
    }
}
