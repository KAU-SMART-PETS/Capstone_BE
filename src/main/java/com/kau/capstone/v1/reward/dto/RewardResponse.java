package com.kau.capstone.v1.reward.dto;

import com.kau.capstone.entity.reward.Reward;

public record RewardResponse(
        Long id,
        String title,
        String content,
        int earnPoint
) {

    public static RewardResponse toResponse(Reward reward) {
        return new RewardResponse(
            reward.getId(),
            reward.getTitle(),
            reward.getContent(),
            reward.getEarnPoint());
    }
}
