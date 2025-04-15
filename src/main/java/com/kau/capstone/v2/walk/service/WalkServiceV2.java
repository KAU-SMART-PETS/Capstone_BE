package com.kau.capstone.v2.walk.service;

import com.kau.capstone.entity.member.Member;
import com.kau.capstone.entity.member.repository.MemberRepository;
import com.kau.capstone.entity.member.repository.OwnedPetRepository;
import com.kau.capstone.entity.pet.Pet;
import com.kau.capstone.entity.walk.Walk;
import com.kau.capstone.entity.walk.repository.WalkRepository;
import com.kau.capstone.v1.auth.dto.LoginInfo;
import com.kau.capstone.v2.walk.dto.request.WalkCreateReqV2;
import com.kau.capstone.v2.walk.dto.response.WalkCreateResV2;
import com.kau.capstone.v2.walk.util.TimeUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WalkServiceV2 {

    private final WalkRepository walkRepository;
    private final MemberRepository memberRepository;
    private final OwnedPetRepository ownedPetRepository;

    @Transactional
    public WalkCreateResV2 createWalk(LoginInfo loginInfo, Long petId, @Valid WalkCreateReqV2 walkCreateReq) {

        Member member = memberRepository.getById(loginInfo.memberId());
        Pet pet = ownedPetRepository.getPetByMemberAndPetId(member, petId);

        Walk walk = Walk.of(member, pet, walkCreateReq);
        walkRepository.save(walk);
        return WalkCreateResV2.of(walk.getStartTime(),walk.getEndTime(), TimeUtils.formatDuration(walk.getDuration()),walk.getDistance(),walk.getKcal(),walk.getStep());
    }
}
