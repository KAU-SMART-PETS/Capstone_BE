package com.kau.capstone.v1.reward.dto;

import com.kau.capstone.entity.reward.Reward;
import lombok.Builder;

import java.util.List;

@Builder
public record RewardsResponse(
        List<RewardResponse> rewards
) {

    public static RewardsResponse toResponse(List<Reward> rewards) {
        List<RewardResponse> responses = rewards.stream()
                .map(RewardResponse::toResponse)
                .toList();

        return RewardsResponse.builder()
                .rewards(responses)
                .build();
    }
}
