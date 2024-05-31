package com.kau.capstone.domain.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record NaverUserInfoResponse(
        @JsonProperty("response") NaverAccount account
) {

    public record NaverAccount(
            String id,
            String name,
            String email
    ) {
    }
}
