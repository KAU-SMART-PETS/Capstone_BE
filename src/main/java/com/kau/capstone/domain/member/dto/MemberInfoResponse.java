package com.kau.capstone.domain.member.dto;

import com.kau.capstone.domain.member.entity.Member;
import lombok.Builder;

@Builder
public record MemberInfoResponse (
        String name,
        String email,
        String phoneNumber,
        Boolean smsOptIn,
        Boolean emailOptIn
) {

    public static MemberInfoResponse toResponse(Member member) {
        return MemberInfoResponse.builder()
                .name(member.getName())
                .email(member.getEmail())
                .phoneNumber(member.getPhoneNumber())
                .smsOptIn(member.getSmsOptIn())
                .emailOptIn(member.getEmailOptIn())
                .build();
    }
}
