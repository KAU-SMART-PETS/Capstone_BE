package com.kau.capstone.v2.reward.dto;

import com.kau.capstone.entity.reward.Reward;

public record RewardResV2(
    long id,
    String title,
    String content,
    int earnPoint
) {

    public static RewardResV2 of(Reward reward) {
        return new RewardResV2(
            reward.getId(),
            reward.getTitle(),
            reward.getContent(),
            reward.getEarnPoint()
        );
    }

}
