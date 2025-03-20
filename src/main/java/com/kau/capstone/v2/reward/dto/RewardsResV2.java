package com.kau.capstone.v2.reward.dto;

import com.kau.capstone.entity.reward.Reward;
import java.util.List;

public record RewardsResV2(
    List<RewardResV2> rewards
) {

    public static RewardsResV2 of(List<Reward> rewards) {
        List<RewardResV2> responses = rewards.stream()
            .map(RewardResV2::of)
            .toList();

        return new RewardsResV2(responses);
    }

}
