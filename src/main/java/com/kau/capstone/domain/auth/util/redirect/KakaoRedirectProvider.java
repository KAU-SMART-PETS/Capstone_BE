package com.kau.capstone.domain.auth.util.redirect;

import com.kau.capstone.domain.auth.util.SocialSite;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.StringJoiner;

@Component
public class KakaoRedirectProvider implements OAuthRedirectProvider {

    private static final String AUTHORIZATION_BASE_URL = "https://kauth.kakao.com";
    private static final String OAUTH_URI = "/oauth/authorize";

    // redirect uri 관련 상수
    private static final String RESPONSE_TYPE_CODE = "response_type=code";
    private static final String REDIRECT_URI = "redirect_uri=";
    private static final String CLIENT_ID = "client_id=";

    private static final String DELIMITER = "&";
    private static final String QUESTION_MARK = "?";

    @Value("${kakao.rest-api-key}")
    private String restApiKey;

    @Value("${kakao.redirect-uri")
    private String redirectUri;

    @Override
    public String getRedirectURL() {
        final StringJoiner joiner = new StringJoiner(DELIMITER)
                .add(RESPONSE_TYPE_CODE)
                .add(CLIENT_ID + restApiKey)
                .add(REDIRECT_URI + redirectUri);

        return AUTHORIZATION_BASE_URL + OAUTH_URI + QUESTION_MARK + joiner;
    }

    @Override
    public SocialSite getSocialSite() {
        return SocialSite.KAKAO;
    }
}
