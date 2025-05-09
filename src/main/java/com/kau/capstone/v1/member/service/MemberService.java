package com.kau.capstone.v1.member.service;

import com.kau.capstone.entity.alarm.Alarm;
import com.kau.capstone.entity.alarm.AlarmDetail;
import com.kau.capstone.entity.alarm.repository.AlarmRepository;
import com.kau.capstone.v1.auth.dto.SignUserDto;
import com.kau.capstone.v1.auth.dto.UserInfoDto;
import com.kau.capstone.v1.member.dto.MemberInfoResponse;
import com.kau.capstone.v1.member.dto.ModifyMemberRequest;
import com.kau.capstone.v1.member.dto.OwnedPetsResponse;
import com.kau.capstone.entity.member.Member;
import com.kau.capstone.entity.member.repository.MemberRepository;
import com.kau.capstone.entity.member.repository.OwnedPetRepository;
import com.kau.capstone.v1.member.util.MemberMapper;
import com.kau.capstone.entity.pet.Pet;
import com.kau.capstone.entity.point.Point;
import com.kau.capstone.entity.point.repository.PointRepository;
import java.util.ArrayList;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final OwnedPetRepository ownedPetRepository;
    private final PointRepository pointRepository;
    private final AlarmRepository alarmRepository;

    @Transactional(propagation = REQUIRES_NEW)
    public SignUserDto findOrCreateMember(String site, UserInfoDto userInfoDto) {
        String platformId = String.valueOf(userInfoDto.id());

        Optional<Member> member = memberRepository.findByPlatformId(platformId);
        if (member.isPresent()) {
            member.get().updateToken(userInfoDto.refreshToken());
            return SignUserDto.of(FALSE, member.get().getId());
        }
        return save(site, userInfoDto);
    }

    private SignUserDto save(String site, UserInfoDto userInfoDto) {

        Member member = MemberMapper.toMember(site, userInfoDto);
        memberRepository.save(member);

        Point point = Point.of(member);

        member.connectPoint(point);

        // 알람 내용 초기 세팅 (모든 알람을 보여주기 위해)
        List<Alarm> alarms = AlarmDetail.getAlarms(member);
        alarmRepository.saveAll(alarms);

        return SignUserDto.of(TRUE, member.getId());
    }

    public Member findById(Long memberId) {
        return memberRepository.getById(memberId);
    }

    @Transactional(propagation = REQUIRES_NEW)
    public void updateRefreshToken(Long memberId, String refreshToken) {
        memberRepository.updateRefreshTokenByMemberId(memberId, refreshToken);
    }

    public MemberInfoResponse getMemberInfo(Long memberId) {
        Member member = memberRepository.getById(memberId);

        return MemberInfoResponse.toResponse(member);
    }

    public void putMemberInfo(Long memberId, ModifyMemberRequest request) {
        Member member = memberRepository.getById(memberId);

        member.updateInfo(request.email(), request.phoneNumber(), request.smsOptIn(), request.emailOptIn());
    }

    public OwnedPetsResponse getOwnedPets(Long memberId) {
        Member member = memberRepository.getById(memberId);

        List<Pet> ownedPets = ownedPetRepository.findPetsByMember(member);

        List<Pet> pets = new ArrayList<>();
        for (Pet pet : ownedPets) {
            if (Objects.isNull(pet.getDeletedAt())) {
                pets.add(pet);
            }
        }

        return OwnedPetsResponse.toResponse(pets);
    }
}
