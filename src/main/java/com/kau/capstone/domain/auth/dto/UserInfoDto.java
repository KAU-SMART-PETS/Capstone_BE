package com.kau.capstone.domain.auth.dto;

import lombok.Builder;

@Builder
public record UserInfoDto(
        String id,
        String nickname,
        String email,
        String refreshToken
) {

    public static UserInfoDto toDto(KakaoUserInfoResponse response, String refreshToken) {
        return UserInfoDto.builder()
                .id(response.id())
                .nickname(response.account().profile().nickname())
                .email(response.account().email())
                .refreshToken(refreshToken)
                .build();
    }

    public static UserInfoDto toDto(NaverUserInfoResponse response, String refreshToken) {
        return UserInfoDto.builder()
                .id(response.account().id())
                .nickname(response.account().name())
                .email(response.account().email())
                .refreshToken(refreshToken)
                .build();
    }
}
