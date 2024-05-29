package com.kau.capstone.domain.auth.dto;

import com.kau.capstone.domain.member.entity.Member;
import lombok.Builder;

@Builder
public record UserInfoDto(
        String id,
        String nickname,
        String email
) {
}
