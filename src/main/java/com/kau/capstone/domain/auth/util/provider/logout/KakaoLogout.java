package com.kau.capstone.domain.auth.util.provider.logout;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class KakaoLogout {

    private static final String LOGOUT_URL = "https://kapi.kakao.com/v1/user/logout";

    private final RestTemplate restTemplate;

    public KakaoLogout(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public void logout(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setBearerAuth(accessToken);

        restTemplate.postForEntity(LOGOUT_URL, new HttpEntity<>(headers), String.class);
    }
}
