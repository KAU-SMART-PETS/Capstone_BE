package com.kau.capstone.domain.walk.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WalkWeeklySummaryResponse {
    private int walkingDistancePercent;     // 주간 일일 산책량 통계
    private int restTimePercent;            // 주간 일일 휴식량 통계
    private int stepCountPercent;           // 주간 일일 걸음수 통계
    private int sunlightExposurePercent;    // 주간 일일 노출량 통계
    private int uvExposurePercent;          // 주간 일일 자외선 노출량 통계
    private int vitaminSynthesisPercent;    // 주간 일일 비타민D 합성량 통계
}
