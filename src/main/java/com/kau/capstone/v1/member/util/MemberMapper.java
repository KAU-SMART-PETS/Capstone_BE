package com.kau.capstone.v1.member.util;

import com.kau.capstone.v1.auth.dto.UserInfoDto;
import com.kau.capstone.entity.member.Member;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberMapper {

    public static Member toMember(String site, UserInfoDto userInfoDto) {
        return Member.builder()
                .platformId(String.valueOf(userInfoDto.id()))
                .socialSite(site)
                .name(userInfoDto.nickname())
                .email(userInfoDto.email())
                .refreshToken(userInfoDto.refreshToken())
                .build();
    }
}
