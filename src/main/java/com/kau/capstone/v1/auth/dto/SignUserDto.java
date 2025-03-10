package com.kau.capstone.v1.auth.dto;

import lombok.Builder;

@Builder
public record SignUserDto(
        Boolean isSignUp,
        Long memberId
) {

    public static SignUserDto of(Boolean isSignUp, Long memberId) {
        return SignUserDto.builder()
                .isSignUp(isSignUp)
                .memberId(memberId)
                .build();
    }
}
