package com.kau.capstone.domain.member.service;

import com.kau.capstone.domain.auth.dto.SignUserDto;
import com.kau.capstone.domain.auth.dto.UserInfoDto;
import com.kau.capstone.domain.member.dto.EarnPointRequest;
import com.kau.capstone.domain.member.dto.MemberInfoResponse;
import com.kau.capstone.domain.member.dto.ModifyMemberRequest;
import com.kau.capstone.domain.member.dto.OwnedPetsResponse;
import com.kau.capstone.domain.member.dto.PayPointRequest;
import com.kau.capstone.domain.member.entity.Member;
import com.kau.capstone.domain.member.entity.pet.OwnedPet;
import com.kau.capstone.domain.member.exception.MemberNotFoundException;
import com.kau.capstone.domain.member.exception.PointNotEnoughException;
import com.kau.capstone.domain.member.repository.MemberRepository;
import com.kau.capstone.domain.member.repository.OwnedPetRepository;
import com.kau.capstone.domain.member.util.MemberMapper;
import com.kau.capstone.domain.pet.entity.Pet;
import com.kau.capstone.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.kau.capstone.global.exception.ErrorCode.MEMBER_NOT_FOUND;
import static com.kau.capstone.global.exception.ErrorCode.POINT_NOT_ENOUGH;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final OwnedPetRepository ownedPetRepository;

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

    public MemberInfoResponse getMemberInfo(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException(MEMBER_NOT_FOUND));

        return MemberInfoResponse.toResponse(member);
    }

    public void putMemberInfo(Long memberId, ModifyMemberRequest request) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException(MEMBER_NOT_FOUND));

        member.updateInfo(request.email(), request.phoneNumber(), request.smsOptIn(), request.emailOptIn());
    }

    public OwnedPetsResponse getOwnedPets(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException(MEMBER_NOT_FOUND));

        List<Pet> ownedPets = ownedPetRepository.findPetsByMember(member);

        return OwnedPetsResponse.toResponse(ownedPets);
    }

    public void processPointPayment(Long memberId, PayPointRequest request) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException(MEMBER_NOT_FOUND));

        if (member.getPoint() < request.point()) {
            throw new PointNotEnoughException(POINT_NOT_ENOUGH);
        }

        member.payment(request.point());
    }

    public void processPointEarn(Long memberId, EarnPointRequest request) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException(MEMBER_NOT_FOUND));

        member.earn(request.point());
    }
}
