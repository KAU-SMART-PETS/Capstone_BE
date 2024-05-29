package com.kau.capstone.domain.auth.dto;

import lombok.Builder;

@Builder
public record UserInfoDto(
        Long id,
        String nickname,
        String email
) {
}
