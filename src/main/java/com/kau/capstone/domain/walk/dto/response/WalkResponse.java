package com.kau.capstone.domain.walk.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
@AllArgsConstructor
@Getter
public class WalkResponse {
    private String startDate;
    private String endDate;
    private String walkingTime;
    private Double distance;
    private Double calories;
    private Long step;

    public WalkResponse(String startDate, String endDate, Double distance, Double calories, Long step) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.distance = distance;
        this.calories = calories;
        this.step = step;
    }
}
