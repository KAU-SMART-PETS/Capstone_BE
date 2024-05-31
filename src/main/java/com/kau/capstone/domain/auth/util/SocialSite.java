package com.kau.capstone.domain.auth.util;

import com.kau.capstone.domain.auth.exception.AuthException;

import java.util.Arrays;

import static com.kau.capstone.global.exception.ErrorCode.SOCIAL_SITE_NOT_SUPPORTED;

public enum SocialSite {

    KAKAO("kakao"),
    NAVER("naver"),
    ;

    private String site;

    SocialSite(String site) {
        this.site = site;
    }

    public static SocialSite findBySocialSite(String site) {
        return Arrays.stream(SocialSite.values())
                .filter(socialSite -> socialSite.site.equals(site))
                .findAny()
                .orElseThrow(() -> new AuthException(SOCIAL_SITE_NOT_SUPPORTED));
    }
}
