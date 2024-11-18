package com.kau.capstone.domain.walk.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@AllArgsConstructor
@Getter
public class WalkResponse {
    private String startDate;
    private String endDate;
    private String walkingTime;
    private Double distance;
    private Double calories;
    private Long step;

    public WalkResponse(LocalDateTime startDate,
                        LocalDateTime endDate,
                        String walkingTime,
                        Double distance,
                        Double calories,
                        Long step) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");
        this.startDate = startDate.format(formatter);
        this.endDate = endDate.format(formatter);
        this.walkingTime = walkingTime;
        this.distance = distance;
        this.calories = calories;
        this.step = step;
    }
}
