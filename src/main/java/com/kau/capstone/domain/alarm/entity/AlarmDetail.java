package com.kau.capstone.domain.alarm.entity;

import com.kau.capstone.domain.member.entity.Member;
import com.kau.capstone.domain.reward.entity.Reward;
import com.kau.capstone.domain.reward.entity.RewardDetail;

import java.util.Arrays;
import java.util.List;

public enum AlarmDetail {

    ONE(1L, "반려동물을 등록해주세요", true),
    TWO(2L, "우리 아이 안전을 위한 비문을 등록해주세요", true),
    TRHEE(3L, "반려동물의 예방 접종을 등록해주세요", true),
    FOUR(4L, "반려동물 산책을 시작해볼까요?", true),
    ;

    private final Long type;
    private final String text;
    private final Boolean isVisible;

    AlarmDetail(Long type, String text, Boolean isVisible) {
        this.type = type;
        this.text = text;
        this.isVisible = isVisible;
    }

    public static List<Alarm> getAlarms(Member member) {
        return Arrays.stream(AlarmDetail.values())
                .map(detail -> Alarm.builder()
                        .member(member)
                        .type(detail.type)
                        .text(detail.text)
                        .isVisible(detail.isVisible)
                        .build())
                .toList();
    }

    public Long type() {
        return this.type;
    }
}
