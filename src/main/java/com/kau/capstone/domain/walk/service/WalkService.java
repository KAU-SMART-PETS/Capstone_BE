package com.kau.capstone.domain.walk.service;

import com.kau.capstone.domain.member.entity.Member;
import com.kau.capstone.domain.member.repository.MemberRepository;
import com.kau.capstone.domain.member.repository.OwnedPetRepository;
import com.kau.capstone.domain.pet.entity.Pet;
import com.kau.capstone.domain.pet.repository.PetRepository;
import com.kau.capstone.domain.walk.dto.request.WalkRequest;
import com.kau.capstone.domain.walk.dto.response.WalkResponse;
import com.kau.capstone.domain.walk.entity.Walk;
import com.kau.capstone.domain.walk.repository.WalkRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WalkService {

    private final WalkRepository walkRepository;
    private final MemberRepository memberRepository;
    private final PetRepository petRepository;
    private final OwnedPetRepository ownedPetRepository;

    @Transactional
    public WalkResponse saveWalkData(WalkRequest walkData) {
        // 유저와 펫 조회
        Member member = memberRepository.findByPlatformId(walkData.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다"));
        Pet pet = petRepository.findById(walkData.getPetSrn())
                .orElseThrow(() -> new IllegalArgumentException("반려동물을 찾을 수 없습니다"));

        // 유저의 소유 반려동물인지 체크
        List<Pet> pets = ownedPetRepository.findPetsByMember(member);
        if (!pets.contains(pet)) {
            throw new IllegalArgumentException("해당 반려동물은 유저의 반려동물이 아닙니다");
        }

        // 엔티티 생성
        Walk walk = Walk.create(
                member,
                pet,
                walkData.getDataInpDt(),
                walkData.getDistance(),
                walkData.getStep(),
                walkData.getWalkingTime(),
                walkData.getTLux(),
                walkData.getAvgK(),
                walkData.getAvgLux(),
                walkData.getStartTime(),
                walkData.getEndTime()
        );

        // 데이터 베이스 반영
        walkRepository.save(walk);

        return new WalkResponse(
                walk.getStartTime(),
                walk.getEndTime(),
                walk.getWalkingTime(),
                walk.getDistance(),
                walk.getKcal(),
                walk.getStep()
        );

    }
}
