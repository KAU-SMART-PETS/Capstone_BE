package com.kau.capstone.domain.walk.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class WalkRequest {
    private String userId;              // 사용자 ID
    private Long petId;                // 반려동물 ID
    private Date dataInpDt;           // 측정 일자
    private Long step;          // 걸음 수
    private Double tLux;                // 누적 조도량
    private Double avgK;                // 평균 색온도
    private Double avgLux;              // 평균 조도량
    private String startTime;        // 산책 시작 시간
    private String endTime;        // 산책 종료 시간
    private Double distance;            // 산책 거리
    private String walkingTime;  // 소요 시간
}
