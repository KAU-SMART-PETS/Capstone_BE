package com.kau.capstone.domain.member.service;

import com.kau.capstone.domain.auth.dto.SignUserDto;
import com.kau.capstone.domain.auth.dto.UserInfoDto;
import com.kau.capstone.domain.member.entity.Member;
import com.kau.capstone.domain.member.exception.MemberNotFoundException;
import com.kau.capstone.domain.member.repository.MemberRepository;
import com.kau.capstone.domain.member.util.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.kau.capstone.global.exception.ErrorCode.MEMBER_NOT_FOUND;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional(propagation = REQUIRES_NEW)
    public SignUserDto findOrCreateMember(String site, UserInfoDto userInfoDto) {
        String platformId = String.valueOf(userInfoDto.id());

        return memberRepository.findByPlatformId(platformId)
                .map(member -> SignUserDto.of(FALSE, member.getId()))
                .orElseGet(() -> save(site, userInfoDto));
    }

    private SignUserDto save(String site, UserInfoDto userInfoDto) {
        Member member = MemberMapper.toMember(site, userInfoDto);
        memberRepository.save(member);

        return SignUserDto.of(TRUE, member.getId());
    }

    public Member findById(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException(MEMBER_NOT_FOUND));
    }

    @Transactional(propagation = REQUIRES_NEW)
    public void updateRefreshToken(Long memberId, String refreshToken) {
        memberRepository.updateRefreshTokenByMemberId(memberId, refreshToken);
    }
}
