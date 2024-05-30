package com.kau.capstone.domain.auth.util.provider.logout;

import com.kau.capstone.domain.auth.util.provider.secret.KakaoSecretValue;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
public class KakaoLogout {

    private static final String LOGOUT_URL = "https://kapi.kakao.com/v1/user/logout";

    private static final String AUTHORIZATION = "Authorization";
    private static final String KAKAO_AK = "KakaoAK ";
    private static final String TARGET_ID_TYPE = "target_id_type";
    private static final String USER_ID = "user_id";
    private static final String TARGET_ID = "target_id";

    private final RestTemplate restTemplate;
    private final KakaoSecretValue secretValue;

    public KakaoLogout(RestTemplateBuilder builder, KakaoSecretValue secretValue) {
        this.restTemplate = builder.build();
        this.secretValue = secretValue;
    }

    public void logout(String platformId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add(AUTHORIZATION, KAKAO_AK + secretValue.getAdminKey());

        final MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add(TARGET_ID_TYPE, USER_ID);
        body.add(TARGET_ID, platformId);

        restTemplate.postForEntity(LOGOUT_URL, new HttpEntity<>(body, headers), String.class);
    }
}
