package com.kau.capstone.v2.walk.dto.response;

public record WalkRecentResV2(
    // 이름
    String petName,
    // 산책 일시
    String walkDate,
    // 산책 시간
    String duration,
    // 이동 거리
    double distance
) {

    public static WalkRecentResV2 of(String petName, String walkDate, String duration, double distance) {
        return new WalkRecentResV2(petName, walkDate, duration, distance);
    }
}
