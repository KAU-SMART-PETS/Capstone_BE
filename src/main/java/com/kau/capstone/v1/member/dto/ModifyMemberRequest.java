package com.kau.capstone.v1.member.dto;

public record ModifyMemberRequest(
        String email,
        String phoneNumber,
        Boolean smsOptIn,
        Boolean emailOptIn
) {
}
