package com.kau.capstone.domain.member.service;

import com.kau.capstone.domain.auth.dto.SignUserDto;
import com.kau.capstone.domain.auth.dto.UserInfoDto;
import com.kau.capstone.domain.member.repository.MemberRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Primary
@Service
@Profile("test")
public class TestMemberService extends MemberService {

    public TestMemberService(MemberRepository memberRepository) {
        super(memberRepository);
    }

    @Override
    @Transactional
    public SignUserDto findOrCreateMember(String site, UserInfoDto userInfoDto) {
        return super.findOrCreateMember(site, userInfoDto);
    }
}
