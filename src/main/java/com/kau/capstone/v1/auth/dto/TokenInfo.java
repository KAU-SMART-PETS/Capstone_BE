package com.kau.capstone.v1.auth.dto;

import lombok.Builder;

@Builder
public record TokenInfo(
    String accessToken,
    String refreshToken
) {

    public static TokenInfo toResponse(KakaoAccessTokenResponse response) {
        return TokenInfo.builder()
                .accessToken(response.accessToken())
                .refreshToken(response.refreshToken())
                .build();
    }

    public static TokenInfo toResponse(NaverAccessTokenResponse response) {
        return TokenInfo.builder()
                .accessToken(response.accessToken())
                .refreshToken(response.refreshToken())
                .build();
    }
}
