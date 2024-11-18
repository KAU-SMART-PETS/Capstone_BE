package com.kau.capstone.domain.walk.service;

import com.kau.capstone.domain.member.entity.Member;
import com.kau.capstone.domain.member.repository.MemberRepository;
import com.kau.capstone.domain.member.repository.OwnedPetRepository;
import com.kau.capstone.domain.pet.entity.Pet;
import com.kau.capstone.domain.pet.repository.PetRepository;
import com.kau.capstone.domain.walk.dto.request.WalkRequest;
import com.kau.capstone.domain.walk.dto.response.WalkDailySummaryResponse;
import com.kau.capstone.domain.walk.dto.response.WalkRecentDataListResponse;
import com.kau.capstone.domain.walk.dto.response.WalkRecentDataResponse;
import com.kau.capstone.domain.walk.dto.response.WalkResponse;
import com.kau.capstone.domain.walk.entity.Walk;
import com.kau.capstone.domain.walk.repository.WalkRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
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
    public WalkResponse saveWalkData(WalkRequest walkData, String platformId, String petName) {

        // 유저 객체 조회
        Member member = memberRepository.findByPlatformId(platformId)
                .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다"));

        // 반려 동물 객체 조회
        Pet pet = petRepository.findByName(petName)
                .orElseThrow(() -> new IllegalArgumentException("반려동물을 찾을 수 없습니다"));

        // 유저의 소유 반려동물인지 체크 -> 없어도 상관 없을 것 같긴하지만?
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
                walk.getStartTime(),    // 시작 시간
                walk.getEndTime(),      // 종료 시간
                walk.getWalkingTime(),  // 산책 시간
                walk.getDistance(),     // 산책 거리
                walk.getKcal(),         // 칼로리 소비량
                walk.getStep()          // 걸음 수
        );

    }

    public WalkRecentDataListResponse getWalkRecentData(String platformId) {
        // 회원 조회
        Member member = memberRepository.findByPlatformId(platformId)
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

    public WalkDailySummaryResponse getDailySummary(Long petId, Date date) {
        Pet pet = petRepository.findById(petId)
                .orElseThrow(() -> new IllegalArgumentException("반려동물을 찾을 수 없습니다"));

        List<Walk> dailyWalks = walkRepository.findByPetAndDataIntDt(pet, date);

        if (dailyWalks.isEmpty()) {
            return new WalkDailySummaryResponse(0, 100, 0, 0, 0, 0); // 휴식량 100%로 반환
        }

        // 산책 데이터 합산
        int totalWalkingDistance = 0;
        int totalSteps = 0;
        int totalSunlightExposure = 0;
        int totalUvExposure = 0;
        int totalVitaminSynthesis = 0;
        int totalWalkingTimeInMinutes = 0;

        for (Walk walk : dailyWalks) {
            totalWalkingDistance += walk.getDistance();
            totalSteps += walk.getStep();
            totalSunlightExposure += walk.getTLux();
            totalUvExposure += walk.getAvgLux();
            totalVitaminSynthesis += walk.getAvgK();

            totalWalkingTimeInMinutes += convertTimeToMinutes(walk.getWalkingTime());
        }

        int dailyTotalTimeInMinutes = 1440;
        int restTimePercent = ((dailyTotalTimeInMinutes - totalWalkingTimeInMinutes) / dailyTotalTimeInMinutes) * 100;

        int dailyGoalDistance = 5000;
        int dailyGoalSteps = 10000;
        int dailyGoalSunlightExposure = 20000;
        int dailyGoalUvExposure = 3000;
        int dailyGoalVitaminSynthesis = 500;

        return new WalkDailySummaryResponse(
                (totalWalkingDistance / dailyGoalDistance) * 100,
                restTimePercent,
                totalSteps / dailyGoalSteps * 100,
                (totalSunlightExposure / dailyGoalSunlightExposure) * 100,
                (totalUvExposure / dailyGoalUvExposure) * 100,
                (totalVitaminSynthesis / dailyGoalVitaminSynthesis) * 100
        );
    }

    private int convertTimeToMinutes(String time) {
        String[] parts = time.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        int seconds = Integer.parseInt(parts[2]);
        return hours * 60 + minutes + (seconds >= 30 ? 1 : 0); // 반올림하여 분 단위 계산
    }
}
