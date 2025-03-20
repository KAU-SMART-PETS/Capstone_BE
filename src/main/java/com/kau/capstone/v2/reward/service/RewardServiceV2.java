package com.kau.capstone.v2.reward.service;

import com.kau.capstone.entity.member.repository.MemberRepository;
import com.kau.capstone.entity.reward.Reward;
import com.kau.capstone.entity.reward.repository.RewardRepository;
import com.kau.capstone.v1.auth.dto.LoginInfo;
import com.kau.capstone.v2.reward.dto.RewardCreateReqV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RewardServiceV2 {

    private final MemberRepository memberRepository;
    private final RewardRepository rewardRepository;

    @Transactional
    public void createReward(LoginInfo loginInfo, RewardCreateReqV2 rewardCreateReq) {
        // Member의 role이 admin인지 확인하는 과정 추가 필요
        memberRepository.getById(loginInfo.memberId());
        Reward reward = Reward.of(rewardCreateReq);
        rewardRepository.save(reward);
    }
}
