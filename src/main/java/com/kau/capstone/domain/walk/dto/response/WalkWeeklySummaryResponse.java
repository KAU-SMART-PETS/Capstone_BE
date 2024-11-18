package com.kau.capstone.domain.walk.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WalkWeeklySummaryResponse {
    private int walkingDistancePercent;
    private int restTimePercent;
    private int stepCountPercent;
    private int sunlightExposurePercent;
    private int uvExposurePercent;
    private int vitaminSynthesisPercent;
}
