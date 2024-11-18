package com.kau.capstone.domain.walk.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@AllArgsConstructor
public class WalkRequest {
    private Date dataInpDt;             // 측정 일자
    private Long step;                  // 걸음 수
    private Double tLux;                // 누적 조도량
    private Double avgK;                // 평균 색온도
    private Double avgLux;              // 평균 조도량
    private LocalDateTime startTime;           // 산책 시작 시간
    private LocalDateTime endTime;             // 산책 종료 시간
    private Double distance;            // 산책 거리
    private String walkingTime;         // 산책 시간
}
