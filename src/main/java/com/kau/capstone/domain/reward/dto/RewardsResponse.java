package com.kau.capstone.domain.reward.dto;

import com.kau.capstone.domain.reward.entity.Reward;
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
