package com.kau.capstone.domain.member.service;

import com.kau.capstone.domain.auth.dto.SignUserDto;
import com.kau.capstone.domain.auth.dto.UserInfoDto;
import com.kau.capstone.domain.member.entity.Member;
import com.kau.capstone.domain.member.repository.MemberRepository;
import com.kau.capstone.domain.member.util.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.lang.Boolean.TRUE;
import static java.lang.Boolean.FALSE;
import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional(propagation = REQUIRES_NEW)
    public SignUserDto findOrCreateMember(UserInfoDto userInfoDto) {
        String platformId = String.valueOf(userInfoDto.id());

        return memberRepository.findByPlatformId(platformId)
                .map(member -> SignUserDto.of(FALSE, member.getId()))
                .orElseGet(() -> save(userInfoDto));
    }

    private SignUserDto save(UserInfoDto userInfoDto) {
        Member member = MemberMapper.toMember(userInfoDto);
        memberRepository.save(member);

        return SignUserDto.of(TRUE, member.getId());
    }
}
