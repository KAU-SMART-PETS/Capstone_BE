package com.kau.capstone.domain.walk.dto.response;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class WalkDailySummaryResponse {
    private int walkingDistancePercent;
    private int restTimePercent;
    private int stepCountPercent;
    private int sunlightExposurePercent;
    private int uvExposurePercent;
    private int vitaminSynthesisPercent;
}
