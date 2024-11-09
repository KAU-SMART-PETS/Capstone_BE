package com.kau.capstone.domain.walk.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class WalkResponse {
    private String startDate;
    private String endDate;
    private String walkingTime;
    private Double distance;
    private Double calories;
    private Long step;

}
