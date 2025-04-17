package com.kau.capstone.v2.walk.dto.response;

public record WalkSumResV2(
    // 누적 산책 시간
    String timeSum,
    // 누적 산책 거리
    double distanceSum,
    // 누적 칼로리
    double kcalSum,
    // 누적 걸음 수
    long stepSum
) {
    public static WalkSumResV2 of(String timeSum, double distanceSum, double kcalSum, long stepSum) {
        return new WalkSumResV2(timeSum, distanceSum, kcalSum, stepSum);
    }
}
