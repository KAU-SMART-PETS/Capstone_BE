package com.kau.capstone.v1.reward.dto;

import com.kau.capstone.entity.reward.Reward;
import lombok.Builder;

@Builder
public record RewardResponse(
        Long id,
        String title,
        String content,
        Long earnPoint,
        Boolean isAchieved,
        Boolean isObtain
) {

    public static RewardResponse toResponse(Reward reward) {
        return RewardResponse.builder()
                .id(reward.getId())
                .title(reward.getTitle())
                .content(reward.getContent())
                .earnPoint(reward.getEarnPoint())
                .isAchieved(reward.getIsAchieved())
                .isObtain(reward.getIsObtain())
                .build();
    }
}
