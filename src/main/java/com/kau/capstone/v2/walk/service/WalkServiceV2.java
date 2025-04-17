package com.kau.capstone.v2.walk.service;

import com.kau.capstone.entity.member.Member;
import com.kau.capstone.entity.member.repository.MemberRepository;
import com.kau.capstone.entity.member.repository.OwnedPetRepository;
import com.kau.capstone.entity.pet.Pet;
import com.kau.capstone.entity.walk.Walk;
import com.kau.capstone.entity.walk.repository.WalkRepository;
import com.kau.capstone.v1.auth.dto.LoginInfo;
import com.kau.capstone.v2.walk.dto.request.WalkCreateReqV2;
import com.kau.capstone.v2.walk.dto.response.WalkCalendarV2;
import com.kau.capstone.v2.walk.dto.response.WalkCreateResV2;
import com.kau.capstone.v2.walk.dto.response.WalkDailyResV2;
import com.kau.capstone.v2.walk.dto.response.WalkRecentResV2;
import com.kau.capstone.v2.walk.util.TimeUtils;
import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WalkServiceV2 {

    private final WalkRepository walkRepository;
    private final MemberRepository memberRepository;
    private final OwnedPetRepository ownedPetRepository;

    // 산책 기록하기
    @Transactional
    public WalkCreateResV2 createWalk(LoginInfo loginInfo, Long petId, @Valid WalkCreateReqV2 walkCreateReq) {

        Member member = memberRepository.getById(loginInfo.memberId());
        Pet pet = ownedPetRepository.getPetByMemberAndPetId(member, petId);

        Walk walk = Walk.of(member, pet, walkCreateReq);
        walkRepository.save(walk);
        return WalkCreateResV2.of(walk.getStartTime(),walk.getEndTime(), TimeUtils.formatDuration(walk.getDuration()),walk.getDistance(),walk.getKcal(),walk.getStep());
    }

    // 최근 산책 기록 가져오기
    @Transactional(readOnly = true)
    public List<WalkRecentResV2> getRecentWalk(LoginInfo loginInfo) {

        Member member = memberRepository.getById(loginInfo.memberId());
        List<Walk> recentWalks = walkRepository.getRecentWalks(member);
        List<WalkRecentResV2> walkRecentRes = new ArrayList<>();

        for (Walk walk : recentWalks) {
            String petName = walk.getPet().getName();
            LocalDate walkDate = walk.getStartTime().toLocalDate();
            String duration = TimeUtils.formatDuration(walk.getDuration());
            double distance = walk.getDistance();

            walkRecentRes.add(WalkRecentResV2.of(petName, walkDate, duration, distance));
        }

        return walkRecentRes;
    }

    // 월간 산책 날짜 가져오기
    @Transactional(readOnly = true)
    public List<WalkCalendarV2> getWalkCalendar(LoginInfo loginInfo, Long petId, LocalDate date) {
        Member member = memberRepository.getById(loginInfo.memberId());
        Pet pet = ownedPetRepository.getPetByMemberAndPetId(member, petId);
        int year = date.getYear();
        int month = date.getMonthValue();
        List<Walk> walks = walkRepository.getWalkCalendar(pet, year, month);

        List<WalkCalendarV2> walkCalendar = new ArrayList<>();
        for (Walk walk : walks) {
            walkCalendar.add(WalkCalendarV2.of(walk.getStartTime().toLocalDate()));
        }

        return walkCalendar;
    }

    // 일일 산책 데이터 가져오기
    @Transactional(readOnly = true)
    public WalkDailyResV2 getDailyWalk(LoginInfo loginInfo, Long petId, LocalDate walkDate) {
        Member member = memberRepository.getById(loginInfo.memberId());
        Pet pet = ownedPetRepository.getPetByMemberAndPetId(member, petId);
        List<Walk> dailyWalks = walkRepository.getDailyWalksByPetAndWalkDate(pet, walkDate);

        long timeSum = 0;
        double distanceSum = 0;
        double kcalSum = 0;
        long stepSum = 0;

        for (Walk walk : dailyWalks) {
            timeSum += walk.getDuration();
            distanceSum += walk.getDistance();
            kcalSum += walk.getKcal();
            stepSum += walk.getStep();
        }
        return WalkDailyResV2.of(TimeUtils.formatDuration(timeSum), distanceSum, kcalSum, stepSum);
    }

}
