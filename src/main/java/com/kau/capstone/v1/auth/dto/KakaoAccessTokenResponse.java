package com.kau.capstone.v1.auth.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record KakaoAccessTokenResponse(
        String tokenType,
        String accessToken,
        Integer expiresIn,
        String refreshToken,
        String refreshTokenExpiresIn
) {
}
