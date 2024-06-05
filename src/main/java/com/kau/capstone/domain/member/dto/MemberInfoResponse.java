package com.kau.capstone.domain.member.dto;

import com.kau.capstone.domain.member.entity.Member;
import lombok.Builder;

@Builder
public record MemberInfoResponse (
        String name,
        String email,
        Long point,
        String socialSite
) {

    public static MemberInfoResponse toResponse(Member member) {
        return MemberInfoResponse.builder()
                .name(member.getName())
                .email(member.getEmail())
                .point(member.getPoint())
                .socialSite(member.getSocialSite())
                .build();
    }
}
