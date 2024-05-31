package com.kau.capstone.domain.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record NaverUserInfoResponse(
        @JsonProperty("response") NaverAccount account
) {

    public UserInfoDto toUserInfo() {
        return UserInfoDto.builder()
                .id(account.id)
                .nickname(account.name)
                .email(account.email)
                .build();
    }

    public record NaverAccount(
            String id,
            String name,
            String email
    ) {
    }
}
