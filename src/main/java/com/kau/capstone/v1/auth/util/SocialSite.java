package com.kau.capstone.v1.auth.util;

import com.kau.capstone.v1.auth.exception.AuthException;

import java.util.Arrays;

import static com.kau.capstone.global.exception.ErrorCode.SOCIAL_SITE_NOT_FOUND;

public enum SocialSite {

    KAKAO("kakao"),
    NAVER("naver"),
    TEST("test"),
    ;

    private final String site;

    SocialSite(String site) {
        this.site = site;
    }

    public static SocialSite findBySocialSite(String site) {
        return Arrays.stream(SocialSite.values())
                .filter(socialSite -> socialSite.site.toLowerCase().equals(site))
                .findAny()
                .orElseThrow(() -> new AuthException(SOCIAL_SITE_NOT_FOUND));
    }
}
