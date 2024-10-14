package com.kau.capstone.domain.member.dto;

import com.kau.capstone.domain.member.entity.Member;
import lombok.Builder;

@Builder
public record MemberInfoResponse (
        String name,
        String email,
        String phoneNumber,
        Long point,
        String socialSite,
        Boolean smsOptIn,
        Boolean emailOptIn
) {

    public static MemberInfoResponse toResponse(Member member) {
        return MemberInfoResponse.builder()
                .name(member.getName())
                .email(member.getEmail())
                .phoneNumber(member.getPhoneNumber())
                .point(member.getPoint())
                .socialSite(member.getSocialSite())
                .smsOptIn(member.getSmsOptIn())
                .emailOptIn(member.getEmailOptIn())
                .build();
    }
}
