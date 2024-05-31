package com.kau.capstone.domain.auth.dto;

import lombok.Builder;

@Builder
public record TokenResponse (
    String accessToken,
    String refreshToken
) {

    public static TokenResponse toResponse(KakaoAccessTokenResponse response) {
        return TokenResponse.builder()
                .accessToken(response.accessToken())
                .refreshToken(response.refreshToken())
                .build();
    }

    public static TokenResponse toResponse(NaverAccessTokenResponse response) {
        return TokenResponse.builder()
                .accessToken(response.accessToken())
                .refreshToken(response.refreshToken())
                .build();
    }
}
