package com.kau.capstone.domain.reward.service;

import com.kau.capstone.domain.member.entity.Member;
import com.kau.capstone.domain.member.exception.MemberNotFoundException;
import com.kau.capstone.domain.member.repository.MemberRepository;
import com.kau.capstone.domain.reward.dto.RewardsResponse;
import com.kau.capstone.domain.reward.entity.Reward;
import com.kau.capstone.domain.reward.repository.RewardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.kau.capstone.global.exception.ErrorCode.MEMBER_NOT_FOUND;

@Service
@Transactional
@RequiredArgsConstructor
public class RewardService {

    private final MemberRepository memberRepository;
    private final RewardRepository rewardRepository;

    public RewardsResponse getRewardsInfo(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException(MEMBER_NOT_FOUND));

        List<Reward> rewards = rewardRepository.findRewardsByMember(member);

        return RewardsResponse.toResponse(rewards);
    }
}
