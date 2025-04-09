package com.kau.capstone.v1.auth.util.provider.redirect;

import com.kau.capstone.v1.auth.util.provider.secret.NaverSecretValue;
import org.springframework.stereotype.Component;

import java.util.StringJoiner;

@Component
public class NaverRedirect {

    private static final String AUTHORIZATION_URL = "https://nid.naver.com/oauth2.0/authorize";

    private static final String RESPONSE_TYPE_CODE = "response_type=code";
    private static final String CLIENT_ID = "client_id=";
    private static final String REDIRECT_URI = "redirect_uri=";

    private static final String DELIMITER = "&";
    private static final String QUESTION_MARK = "?";
    public static final String STATE_VALUE_PREVENT_CSRF = "state=123";

    private final NaverSecretValue secretValue;

    public NaverRedirect(NaverSecretValue secretValue) {
        this.secretValue = secretValue;
    }

    public String getRedirectURL() {
        String clientId = secretValue.getClientId();
        String redirectUri = secretValue.getRedirectUri();

        StringJoiner joiner = new StringJoiner(DELIMITER)
                .add(RESPONSE_TYPE_CODE)
                .add(CLIENT_ID + clientId)
                .add(REDIRECT_URI + redirectUri)
                .add(STATE_VALUE_PREVENT_CSRF);

        return AUTHORIZATION_URL + QUESTION_MARK + joiner;
    }
}
