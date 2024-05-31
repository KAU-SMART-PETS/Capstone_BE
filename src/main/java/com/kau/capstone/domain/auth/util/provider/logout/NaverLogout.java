package com.kau.capstone.domain.auth.util.provider.logout;

import com.kau.capstone.domain.auth.util.provider.secret.NaverSecretValue;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
public class NaverLogout {

    private static final String LOGOUT_URL = "`https://nid.naver.com/oauth2.0/token";

    private static final String DELETE = "delete";
    private static final String REFRESH_TOKEN = "refresh_token";

    private static final String GRANT_TYPE = "grant_type";
    private static final String CLIENT_ID = "client_id";
    public static final String CLIENT_SECRET = "client_secret";
    public static final String ACCESS_TOKEN = "access_token";
    public static final String SERVICE_PROVIDER = "service_provider";
    public static final String NAVER = "NAVER";

    private final RestTemplate restTemplate;
    private final NaverSecretValue secretValue;

    public NaverLogout(RestTemplateBuilder restTemplateBuilder, NaverSecretValue secretValue) {
        this.restTemplate = restTemplateBuilder.build();
        this.secretValue = secretValue;
    }

    // 카카오와 다르게 네이버는 어드민이 직접 로그아웃이 불가능합니다.
    // 출처: https://developers.naver.com/docs/login/api/api.md 에서 로그아웃 항목
    public void logout(String refreshToken) {
        // String newAccessToken = updateRefreshToken(refreshToken);

        HttpHeaders headers = new HttpHeaders();
        headers.set(GRANT_TYPE, DELETE);
        headers.set(CLIENT_ID, secretValue.getClientId());
        headers.set(CLIENT_SECRET, secretValue.getClientSecret());
        headers.set(ACCESS_TOKEN, "newAccessToken");
        headers.set(SERVICE_PROVIDER, NAVER);

        restTemplate.postForEntity(LOGOUT_URL, headers, String.class);
    }

    private void updateRefreshToken(String refreshToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(GRANT_TYPE, REFRESH_TOKEN);
        headers.set(CLIENT_ID, secretValue.getClientId());
        headers.set(CLIENT_SECRET, secretValue.getClientSecret());
        headers.set(REFRESH_TOKEN, refreshToken);
        headers.set(SERVICE_PROVIDER, NAVER);

        restTemplate.postForEntity(LOGOUT_URL, headers, String.class);
    }
}
