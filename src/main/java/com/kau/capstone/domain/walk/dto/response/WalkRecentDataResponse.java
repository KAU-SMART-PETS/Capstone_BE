package com.kau.capstone.domain.walk.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class WalkRecentDataResponse {
    private String petName;
    private String walkDate;
    private String walkTime;
    private Double distance;
}
