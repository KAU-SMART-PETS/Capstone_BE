package com.kau.capstone.domain.member.service;

import com.kau.capstone.entity.alarm.repository.AlarmRepository;
import com.kau.capstone.v1.auth.dto.SignUserDto;
import com.kau.capstone.v1.auth.dto.UserInfoDto;
import com.kau.capstone.entity.member.repository.MemberRepository;
import com.kau.capstone.entity.member.repository.OwnedPetRepository;
import com.kau.capstone.entity.point.repository.PointRepository;
import com.kau.capstone.entity.reward.repository.RewardRepository;
import com.kau.capstone.v1.member.service.MemberService;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Primary
@Service
@Profile("test")
public class TestMemberService extends MemberService {

    public TestMemberService(MemberRepository memberRepository,
                             OwnedPetRepository ownedPetRepository,
                             PointRepository pointRepository,
                             RewardRepository rewardRepository,
                             AlarmRepository alarmRepository) {
        super(memberRepository, ownedPetRepository, pointRepository, rewardRepository, alarmRepository);
    }

    @Override
    @Transactional
    public SignUserDto findOrCreateMember(String site, UserInfoDto userInfoDto) {
        return super.findOrCreateMember(site, userInfoDto);
    }
}
