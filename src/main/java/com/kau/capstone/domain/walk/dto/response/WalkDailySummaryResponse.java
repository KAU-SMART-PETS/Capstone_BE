package com.kau.capstone.domain.walk.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WalkDailySummaryResponse {
    private int walkingDistancePercent;     // 일일 산책량
    private int restTimePercent;            // 휴식량
    private int stepCountPercent;           // 걸음 수
    private int sunlightExposurePercent;    // 일광 노출량
    private int uvExposurePercent;          // 자외선 노출량
    private int vitaminSynthesisPercent;    // 비타민 D 합성량
}
