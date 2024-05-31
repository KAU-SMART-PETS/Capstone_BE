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

    public void logout(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(GRANT_TYPE, DELETE);
        headers.set(CLIENT_ID, secretValue.getClientId());
        headers.set(CLIENT_SECRET, secretValue.getClientSecret());
        headers.set(ACCESS_TOKEN, accessToken);
        headers.set(SERVICE_PROVIDER, NAVER);

        restTemplate.postForEntity(LOGOUT_URL, headers, String.class);
    }
}
