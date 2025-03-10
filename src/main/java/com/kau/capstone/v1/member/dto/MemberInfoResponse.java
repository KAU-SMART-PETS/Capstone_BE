package com.kau.capstone.v1.member.dto;

import com.kau.capstone.entity.member.Member;
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
                .point(member.getPoint().getAmount())
                .socialSite(member.getSocialSite())
                .smsOptIn(member.getSmsOptIn())
                .emailOptIn(member.getEmailOptIn())
                .build();
    }
}
