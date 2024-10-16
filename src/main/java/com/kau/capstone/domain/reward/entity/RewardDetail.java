package com.kau.capstone.domain.reward.entity;

import com.kau.capstone.domain.member.entity.Member;

import java.util.Arrays;
import java.util.List;

public enum RewardDetail {

    ONE(1L, "반려동물 등록하기", "반려동물을 등록하면 포인트를 드립니다!", 10000L, false, false),
    TWO(2L, "블루투스 기기 등록하기", "블루투스 기기를 등록해보아요!", 10000L, false, false),
    THREE(3L, "사료 구매하기", "포인트를 모아서 사료를 구매해봐요!", 10000L, false, false),
    FOUR(4L, "산책 시작하기", "산책 기능을 사용해보세요!", 10000L, false, false),
    FIVE(5L, "7일 연속 30분 이상 산책하기", "7일 연속 30분 이상 산책에 성공해보세요!", 10000L, false, false),
    ;

    private final Long type;
    private final String title;
    private final String content;
    private final Long earnPoint;
    private final Boolean isAchieved;
    private final Boolean isObtain;

    RewardDetail(Long type, String title, String content, Long earnPoint, Boolean isAchieved, Boolean isObtain) {
        this.type = type;
        this.title = title;
        this.content = content;
        this.earnPoint = earnPoint;
        this.isAchieved = isAchieved;
        this.isObtain = isObtain;
    }

    public static List<Reward> getRewards(Member member) {
        return Arrays.stream(RewardDetail.values())
                .map(detail -> Reward.builder()
                        .type(detail.type)
                        .member(member)
                        .title(detail.title)
                        .content(detail.content)
                        .earnPoint(detail.earnPoint)
                        .isAchieved(detail.isAchieved)
                        .isObtain(detail.isObtain)
                        .build())
                .toList();
    }

    public Long type() {
        return this.type;
    }
}
