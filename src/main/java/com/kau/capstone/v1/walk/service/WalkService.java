package com.kau.capstone.v1.walk.service;

import com.kau.capstone.entity.member.Member;
import com.kau.capstone.entity.member.repository.MemberRepository;
import com.kau.capstone.entity.member.repository.OwnedPetRepository;
import com.kau.capstone.entity.pet.Pet;
import com.kau.capstone.entity.walk.Walk;
import com.kau.capstone.entity.walk.repository.WalkRepository;
import com.kau.capstone.v1.walk.dto.request.WalkRequest;
import com.kau.capstone.v1.walk.dto.response.WalkDailySummaryResponse;
import com.kau.capstone.v1.walk.dto.response.WalkMonthlyResponse;
import com.kau.capstone.v1.walk.dto.response.WalkRecentDataListResponse;
import com.kau.capstone.v1.walk.dto.response.WalkRecentDataResponse;
import com.kau.capstone.v1.walk.dto.response.WalkResponse;
import com.kau.capstone.v1.walk.dto.response.WalkWeeklySummaryListResponse;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WalkService {

    private final WalkRepository walkRepository;
    private final MemberRepository memberRepository;
    private final OwnedPetRepository ownedPetRepository;


    @Transactional
    public WalkResponse saveWalkData(WalkRequest walkData, Long memberId, Long petId) {

        Member member = memberRepository.findById(memberId)
            .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다"));

        Pet pet = ownedPetRepository.findPetByMemberAndPetId(memberId, petId)
            .orElseThrow(() -> new IllegalArgumentException("해당 반려동물은 유저의 반려동물이 아닙니다"));

        Walk walk = Walk.of(member, pet, walkData);

        walkRepository.save(walk);
        return new WalkResponse(
            walk.getStartTime(),    // 시작 시간
            walk.getEndTime(),      // 종료 시간
            Walk.calcWalkTime(walk.getStartTime(),walk.getEndTime()),  // 산책 시간
            walk.getDistance(),     // 산책 거리
            walk.getKcal(),         // 칼로리 소비량
            walk.getStep()          // 걸음 수
        );

    }

    // 최근 산책데이터 반환
    @Transactional(readOnly = true)
    public WalkRecentDataListResponse getWalkRecentData(Long memberId) {
        // 회원 조회
        Member member = memberRepository.findById(memberId)
            .orElseThrow(() -> new IllegalArgumentException("멤버를 찾을 수 없습니다"));

        // 회원이 소유한 반려동물 목록 조회
        List<Pet> pets = ownedPetRepository.findPetsByMember(member);

        // 날짜 포맷 설정
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy.MM.dd");

        // 각 반려동물의 최근 5개 산책 기록을 가져옴
        List<WalkRecentDataResponse> recentWalks = pets.stream()
            .flatMap(
                pet -> walkRepository.findTop5ByPetOrderByDataIntDtDesc(pet, Pageable.ofSize(5))
                    .stream()
                    .map(walk -> new WalkRecentDataResponse(
                        pet.getName(),                     // 반려동물 이름
                        walk.getStartTime().format(dateFormat), // 측정 일자
                        Walk.calcWalkTime(walk.getStartTime(), walk.getEndTime()),             // 산책 시간
                        walk.getDistance()                 // 산책 거리
                    )))
            .collect(Collectors.toList());

        return new WalkRecentDataListResponse(recentWalks);
    }

    // 일일 산책데이터
    @Transactional(readOnly = true)
    public WalkDailySummaryResponse getDailySummary(Long memberId, Long petId, LocalDate date) {

        // 유저 객체 조회
        Member member = memberRepository.findById(memberId)
            .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다"));

        // 반려동물 객체 조회
        Pet pet = ownedPetRepository.findPetByMemberAndPetId(memberId, petId)
            .orElseThrow(() -> new IllegalArgumentException("해당 반려동물은 유저의 반려동물이 아닙니다"));

        // 해당 날짜의 산책 데이터 조회
        List<Walk> dailyWalks = walkRepository.getDailyWalks(pet, date);

        // 일일 데이터 합산
        double totalDistance = dailyWalks.stream().mapToDouble(Walk::getDistance).sum();
        long totalSteps = dailyWalks.stream().mapToLong(Walk::getStep).sum();
        long totalWalkingTime = dailyWalks.stream().mapToLong(Walk::getDuration).sum();


        // 기준 값 (예: 최대값 설정 또는 동적 계산)
        final double MAX_DISTANCE = 5000.0;  // 5km(m 단위)
        final long MAX_STEPS = 10000;        // 10,000보
        final long MAX_WALKING_TIME = 7200;  // 2시간 (초 단위)

        // 퍼센트 계산
        int walkingDistancePercent = (int) Math.min((totalDistance / MAX_DISTANCE) * 100, 100);
        int stepCountPercent = (int) Math.min((double) totalSteps / MAX_STEPS * 100, 100);
        int walkingTimePercent = (int) Math.min((double) totalWalkingTime / MAX_WALKING_TIME * 100,
            100);

        // 응답 생성
        return new WalkDailySummaryResponse(
            date,
            walkingDistancePercent,
            100 - walkingTimePercent, // 휴식량은 전체 시간에서 걷는 시간을 뺀 값으로 가정
            stepCountPercent,
            0,
            0,
            0
        );
    }

    // 월간 산책 데이터 날짜 반환
    @Transactional(readOnly = true)
    public WalkMonthlyResponse getMonthlyWalkData(Long memberId, Long petId, int year, int month) {
        // 유저 객체 조회
        Member member = memberRepository.findById(memberId)
            .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다"));

        // 반려동물 객체 조회
        Pet pet = ownedPetRepository.findPetByMemberAndPetId(memberId, petId)
            .orElseThrow(() -> new IllegalArgumentException("해당 반려동물은 유저의 반려동물이 아닙니다"));

        // 해당 년/월에 해당하는 산책 기록 조회
        List<Walk> monthlyWalks = walkRepository.findByPetAndYearAndMonth(pet, year, month);

        // 산책 날짜 리스트 추출
        List<LocalDate> walkDates = monthlyWalks.stream()
            .map(Walk::getStartTime)       // LocalDateTime
            .map(LocalDateTime::toLocalDate) // → LocalDate
            .distinct()
            .sorted()
            .collect(Collectors.toList());

        // 응답 생성
        return new WalkMonthlyResponse(walkDates);
    }

    @Transactional(readOnly = true)
    public WalkWeeklySummaryListResponse getWeeklySummaryList(Long memberId, Long petId,
        LocalDate date) {
        // 유저 객체 조회
        Member member = memberRepository.findById(memberId)
            .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다"));

        // 반려동물 객체 조회
        Pet pet = ownedPetRepository.findPetByMemberAndPetId(memberId, petId)
            .orElseThrow(() -> new IllegalArgumentException("해당 반려동물은 유저의 반려동물이 아닙니다"));

        // 주간 날짜 범위 계산 (월요일 ~ 일요일)
        LocalDate startOfWeek = date.with(java.time.DayOfWeek.MONDAY);
        LocalDate endOfWeek = date.with(java.time.DayOfWeek.SUNDAY);

        // 주간 데이터를 날짜별로 조회
        List<LocalDate> weekDates = startOfWeek.datesUntil(endOfWeek.plusDays(1))
            .toList();
        List<WalkDailySummaryResponse> dailySummaries = weekDates.stream()
            .map(day -> {
                List<Walk> dailyWalks = walkRepository.getDailyWalks(pet, day);

                // 일별 통계 계산
                double totalDistance = dailyWalks.stream().mapToDouble(Walk::getDistance).sum();
                long totalSteps = dailyWalks.stream().mapToLong(Walk::getStep).sum();
                long totalWalkingTime = dailyWalks.stream().mapToLong(Walk::getDuration).sum();


                // 기준 값 설정
                final double MAX_DISTANCE = 5000.0;  // 5km
                final long MAX_STEPS = 10000;        // 10,000보
                final long MAX_WALKING_TIME = 7200;  // 2시간

                // 퍼센트 계산
                int walkingDistancePercent = (int) Math.min((totalDistance / MAX_DISTANCE) * 100,
                    100);
                int stepCountPercent = (int) Math.min((double) totalSteps / MAX_STEPS * 100, 100);
                int walkingTimePercent = (int) Math.min(
                    (double) totalWalkingTime / MAX_WALKING_TIME * 100, 100);

                return new WalkDailySummaryResponse(
                    day,                        // 날짜 추가
                    walkingDistancePercent,
                    100 - walkingTimePercent,  // 휴식량
                    stepCountPercent,
                    0,
                    0,
                    0
                );
            }).collect(Collectors.toList());

        return new WalkWeeklySummaryListResponse(dailySummaries);
    }
}
