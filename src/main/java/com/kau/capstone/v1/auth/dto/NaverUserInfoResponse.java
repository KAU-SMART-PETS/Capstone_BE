package com.kau.capstone.v1.auth.dto;

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
