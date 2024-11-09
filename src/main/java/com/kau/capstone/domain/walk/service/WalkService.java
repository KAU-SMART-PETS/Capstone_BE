package com.kau.capstone.domain.walk.service;

import com.kau.capstone.domain.member.entity.Member;
import com.kau.capstone.domain.member.repository.MemberRepository;
import com.kau.capstone.domain.member.repository.OwnedPetRepository;
import com.kau.capstone.domain.pet.entity.Pet;
import com.kau.capstone.domain.pet.repository.PetRepository;
import com.kau.capstone.domain.walk.dto.request.WalkRequest;
import com.kau.capstone.domain.walk.dto.response.WalkRecentDataListResponse;
import com.kau.capstone.domain.walk.dto.response.WalkRecentDataResponse;
import com.kau.capstone.domain.walk.dto.response.WalkResponse;
import com.kau.capstone.domain.walk.entity.Walk;
import com.kau.capstone.domain.walk.repository.WalkRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

        Pet pet = petRepository.findById(walkData.getPetId())
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

        // 산책 데이터 반환
        return new WalkResponse(
                walk.getStartTime(), // 시작 시간
                walk.getEndTime(),  // 종료 시간
                walk.getWalkingTime(), // 산책 시간
                walk.getDistance(), // 산책 거리
                walk.getKcal(), // 칼로리 소비량
                walk.getStep()  // 걸음 수
        );

    }

    public WalkRecentDataListResponse getWalkRecentData(Long memberId) {
        // 회원 조회
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("멤버를 찾을 수 없습니다"));

        // 회원이 소유한 반려동물 목록 조회
        List<Pet> pets = ownedPetRepository.findPetsByMember(member);

        // 날짜 포맷 설정
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");

        // 각 반려동물의 가장 최근 산책 기록을 가져옴
        List<WalkRecentDataResponse> recentWalks = pets.stream()
                .map(pet -> {
                    Walk walk = walkRepository.findTopByPetOrderByDataIntDtDesc(pet)
                            .orElse(null);

                    // 산책 기록이 있을 경우에만 데이터를 반환
                    return walk != null ? new WalkRecentDataResponse(
                            pet.getName(),  // 반려동물 이름
                            dateFormat.format(walk.getDataIntDt()), // 측정 일자
                            walk.getWalkingTime(),  // 산책 시간
                            walk.getDistance()  // 산책 거리
                    ) : null;
                })
                .filter(Objects::nonNull)  // null 값을 필터링
                .collect(Collectors.toList());

        return new WalkRecentDataListResponse(recentWalks);
    }
}
