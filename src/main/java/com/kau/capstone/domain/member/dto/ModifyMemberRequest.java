package com.kau.capstone.domain.member.dto;

public record ModifyMemberRequest(
        String email,
        String phoneNumber,
        Boolean smsOptIn,
        Boolean emailOptIn
) {
}
