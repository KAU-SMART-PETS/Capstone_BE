package com.kau.capstone.domain.auth.util.provider.redirect;

import com.kau.capstone.domain.auth.util.provider.secret.KakaoSecretValue;
import org.springframework.stereotype.Component;

import java.util.StringJoiner;

@Component
public class KakaoRedirect {

    private static final String AUTHORIZATION_URL = "https://kauth.kakao.com/oauth/authorize";

    private static final String RESPONSE_TYPE_CODE = "response_type=code";
    private static final String REDIRECT_URI = "redirect_uri=";
    private static final String CLIENT_ID = "client_id=";

    private static final String DELIMITER = "&";
    private static final String QUESTION_MARK = "?";

    private final KakaoSecretValue secretValue;

    public KakaoRedirect(KakaoSecretValue secretValue) {
        this.secretValue = secretValue;
    }

    public String getRedirectURL() {
        String restApiKey = secretValue.getRestApiKey();
        String redirectUri = secretValue.getRedirectUri();

        final StringJoiner joiner = new StringJoiner(DELIMITER)
                .add(RESPONSE_TYPE_CODE)
                .add(CLIENT_ID + restApiKey)
                .add(REDIRECT_URI + redirectUri);

        return AUTHORIZATION_URL + QUESTION_MARK + joiner;
    }
}
