package com.kau.capstone.v2.reward.util;

import com.kau.capstone.entity.reward.Reward;
import com.kau.capstone.entity.reward.repository.RewardRepository;
import com.kau.capstone.v2.reward.dto.RewardResV2;
import com.kau.capstone.v2.reward.dto.RewardsResV2;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DTOMapper {

    private final RewardRepository rewardRepository;

    public RewardsResV2 toRewards() {
        List<Reward> rewards = rewardRepository.findAll();
        RewardsResV2 response = RewardsResV2.of(rewards);
        return response;
    }

    public RewardResV2 toReward(long rewardId) {
        Reward reward = rewardRepository.getById(rewardId);
        return RewardResV2.of(reward);
    }

}
