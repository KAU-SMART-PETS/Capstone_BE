package com.kau.capstone.domain.auth.util;

import java.util.Arrays;

public enum SocialSite {

    KAKAO("kakao");

    private String site;

    SocialSite(String site) {
        this.site = site;
    }

    public static SocialSite findBySocialSite(String site) {
        return Arrays.stream(SocialSite.values())
                .filter(socialSite -> socialSite.site.equals(site))
                .findAny()
                .orElseThrow(() -> new RuntimeException("지원하지 않는 소셜 로그인 사이트입니다."));
    }
}
