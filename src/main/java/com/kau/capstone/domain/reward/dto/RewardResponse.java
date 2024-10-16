package com.kau.capstone.domain.reward.dto;

import com.kau.capstone.domain.reward.entity.Reward;
import lombok.Builder;

@Builder
public record RewardResponse(
        Long id,
        String title,
        String content,
        Long earnPoint,
        Boolean isAchieved
) {

    public static RewardResponse toResponse(Reward reward) {
        return RewardResponse.builder()
                .id(reward.getId())
                .title(reward.getTitle())
                .content(reward.getContent())
                .earnPoint(reward.getEarnPoint())
                .isAchieved(reward.getIsAchieved())
                .build();
    }
}
