package com.kau.capstone.domain.walk.service;

import com.kau.capstone.domain.member.entity.Member;
import com.kau.capstone.domain.member.repository.MemberRepository;
import com.kau.capstone.domain.member.repository.OwnedPetRepository;
import com.kau.capstone.domain.pet.entity.Pet;
import com.kau.capstone.domain.pet.repository.PetRepository;
import com.kau.capstone.domain.walk.dto.request.WalkRequest;
import com.kau.capstone.domain.walk.dto.response.*;
import com.kau.capstone.domain.walk.entity.Walk;
import com.kau.capstone.domain.walk.repository.WalkRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
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

    public WalkDailySummaryResponse getDailySummary(String platformId, String petName, LocalDate date) {
        // 반려동물 조회
        Pet pet = petRepository.findByName(petName)
                .orElseThrow(() -> new IllegalArgumentException("반려동물을 찾을 수 없습니다"));

        // 해당 날짜의 산책 데이터 조회
        List<Walk> dailyWalks = walkRepository.findByPetAndDataIntDt(pet, date);

        // 일일 데이터 합산
        double totalDistance = dailyWalks.stream().mapToDouble(Walk::getDistance).sum();
        long totalSteps = dailyWalks.stream().mapToLong(Walk::getStep).sum();
        long totalWalkingTime = dailyWalks.stream().mapToLong(Walk::getWalkingTime).sum(); // 초 단위 합산
        double totalLux = dailyWalks.stream().mapToDouble(Walk::getTLux).sum();
        double avgK = dailyWalks.stream().mapToDouble(Walk::getAvgK).average().orElse(0);
        double avgLux = dailyWalks.stream().mapToDouble(Walk::getAvgLux).average().orElse(0);

        // 기준 값 (예: 최대값 설정 또는 동적 계산)
        final double MAX_DISTANCE = 5000.0;  // 5km(m 단위)
        final long MAX_STEPS = 10000;        // 10,000보
        final long MAX_WALKING_TIME = 7200;  // 2시간 (초 단위)
        final double MAX_LUX = 10000.0;      // 일일 최대 조도량
        final double MAX_UV = 8000.0;        // 일일 최대 자외선 노출
        final double MAX_VITAMIN = 5000.0;   // 일일 최대 비타민 D 합성량

        // 퍼센트 계산
        int walkingDistancePercent = (int) Math.min((totalDistance / MAX_DISTANCE) * 100, 100);
        int stepCountPercent = (int) Math.min((double) totalSteps / MAX_STEPS * 100, 100);
        int walkingTimePercent = (int) Math.min((double) totalWalkingTime / MAX_WALKING_TIME * 100, 100);
        int sunlightExposurePercent = (int) Math.min((totalLux / MAX_LUX) * 100, 100);
        int uvExposurePercent = (int) Math.min((avgK / MAX_UV) * 100, 100); // 평균 자외선 노출량 사용
        int vitaminSynthesisPercent = (int) Math.min((avgLux / MAX_VITAMIN) * 100, 100); // 평균 조도량 사용

        // 응답 생성
        return new WalkDailySummaryResponse(
                walkingDistancePercent,
                100 - walkingTimePercent, // 휴식량은 전체 시간에서 걷는 시간을 뺀 값으로 가정
                stepCountPercent,
                sunlightExposurePercent,
                uvExposurePercent,
                vitaminSynthesisPercent
        );
    }

    public WalkMonthlyResponse getMonthlyWalkData(String platformId, String petName, int year, int month) {
        // 반려동물 조회
        Pet pet = petRepository.findByName(petName)
                .orElseThrow(() -> new IllegalArgumentException("반려동물을 찾을 수 없습니다"));

        // 해당 년/월에 해당하는 산책 기록 조회
        List<Walk> monthlyWalks = walkRepository.findByPetAndYearAndMonth(pet, year, month);

        // 산책 날짜 리스트 추출
        List<LocalDate> walkDates = monthlyWalks.stream()
                .map(Walk::getDataIntDt)
                .distinct() // 중복 제거
                .sorted()   // 날짜 정렬
                .collect(Collectors.toList());

        // 응답 생성
        return new WalkMonthlyResponse(walkDates);
    }
}
