package com.kau.capstone.domain.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;


public record KakaoUserInfoResponse(
        String id,
        @JsonProperty("kakao_account") KakaoAccount account
) {
    public UserInfoDto toUserInfo() {
        return UserInfoDto.builder()
                .id(id)
                .nickname(account.profile.nickname)
                .email(account.email)
                .build();
    }

    public record KakaoAccount(
            KakaoProfile profile,
            String email
    ) {
        public record KakaoProfile(
                String nickname,

                @JsonProperty("thumbnail_image_url") String thumbnailImageUrl
        ) {
        }
    }
}
