package com.kau.capstone.v2.reward.dto;

import com.kau.capstone.entity.reward.Reward;
import java.time.LocalDateTime;

public record RewardResV2(
    long id,
    String title,
    String content,
    int earnPoint,
    LocalDateTime createdAt,
    LocalDateTime updatedAt,
    LocalDateTime deletedAt
) {

    public static RewardResV2 of(Reward reward) {
        return new RewardResV2(
            reward.getId(),
            reward.getTitle(),
            reward.getContent(),
            reward.getEarnPoint(),
            reward.getCreatedAt(),
            reward.getUpdatedAt(),
            reward.getDeletedAt()
        );
    }

}
