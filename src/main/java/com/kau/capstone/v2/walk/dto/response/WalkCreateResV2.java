package com.kau.capstone.v2.walk.dto.response;

import java.time.LocalDateTime;

public record WalkCreateResV2(
    // 산책 시작 시간
    LocalDateTime startTime,
    // 산책 종료 시간
    LocalDateTime endTime,
    // 산책 시간
    String duration,
    // 이동 거리
    double distance,
    // 소모 칼로리
    double kcal,
    // 걸음 수
    long step

) {
    public static WalkCreateResV2 of(LocalDateTime startTime, LocalDateTime endTime, String duration, double distance, double kcal, long step) {
        return new WalkCreateResV2(startTime, endTime, duration, distance, kcal, step);
    }
}
