package com.kau.capstone.v1.reward.service;

import com.kau.capstone.entity.member.Member;
import com.kau.capstone.entity.member.repository.MemberRepository;
import com.kau.capstone.v1.reward.dto.RewardsResponse;
import com.kau.capstone.entity.reward.Reward;
import com.kau.capstone.entity.reward.repository.RewardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RewardService {

    private final MemberRepository memberRepository;
    private final RewardRepository rewardRepository;

    public RewardsResponse getRewardsInfo(Long memberId) {
        Member member = memberRepository.getById(memberId);

        List<Reward> rewards = rewardRepository.findAll();

        return RewardsResponse.toResponse(rewards);
    }
}
