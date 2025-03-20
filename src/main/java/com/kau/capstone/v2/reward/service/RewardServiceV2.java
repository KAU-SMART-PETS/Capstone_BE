package com.kau.capstone.v2.reward.service;

import com.kau.capstone.entity.member.repository.MemberRepository;
import com.kau.capstone.entity.reward.Reward;
import com.kau.capstone.entity.reward.repository.RewardRepository;
import com.kau.capstone.v1.auth.dto.LoginInfo;
import com.kau.capstone.v2.reward.dto.RewardCreateReqV2;
import com.kau.capstone.v2.reward.dto.RewardResV2;
import com.kau.capstone.v2.reward.dto.RewardUpdateReqV2;
import com.kau.capstone.v2.reward.dto.RewardsResV2;
import com.kau.capstone.v2.reward.util.DTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RewardServiceV2 {

    private final MemberRepository memberRepository;
    private final RewardRepository rewardRepository;
    private final DTOMapper dtoMapper;

    @Transactional
    public void createReward(LoginInfo loginInfo, RewardCreateReqV2 rewardCreateReq) {
        // Member의 role이 admin인지 확인하는 과정 추가 필요
        memberRepository.getById(loginInfo.memberId());
        Reward reward = Reward.of(rewardCreateReq);
        rewardRepository.save(reward);
    }

    @Transactional(readOnly = true)
    public RewardsResV2 getRewards(LoginInfo loginInfo) {
        // Member의 role이 admin인지 확인하는 과정 추가 필요
        memberRepository.getById(loginInfo.memberId());

        return dtoMapper.toRewards();
    }

    @Transactional(readOnly = true)
    public RewardResV2 getReward(LoginInfo loginInfo, long rewardId) {
        // Member의 role이 admin인지 확인하는 과정 추가 필요
        memberRepository.getById(loginInfo.memberId());

        return dtoMapper.toReward(rewardId);
    }

    @Transactional
    public void updateReward(LoginInfo loginInfo, long rewardId, RewardUpdateReqV2 request) {
        // Member의 role이 admin인지 확인하는 과정 추가 필요
        memberRepository.getById(loginInfo.memberId());

        Reward reward = rewardRepository.getById(rewardId);
        System.out.println(reward.getId());

        reward.updateReward(request);
        System.out.println(reward.getId());
        rewardRepository.save(reward);
    }

    @Transactional
    public void deleteReward(LoginInfo loginInfo, long rewardId) {
        // Member의 role이 admin인지 확인하는 과정 추가 필요
        memberRepository.getById(loginInfo.memberId());

        Reward reward = rewardRepository.getById(rewardId);
        rewardRepository.delete(reward);
    }
}
