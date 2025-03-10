package com.kau.capstone.v1.walk.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class WalkRecentDataResponse {
    private String petName;
    private String walkDate;
    private Long walkTime;
    private Double distance;
}
