package com.kau.capstone.global.common;

public enum PetType {
    CAT(1), DOG(2);

    private final Integer value;

    PetType(int value) {
        this.value = value;
    }

    // 정수를 ENUM으로 변환하는 메서드
    public static PetType fromInt(int value) {
        if (value == 1) {
            return CAT;
        } else if (value == 2) {
            return DOG;
        }

        throw new IllegalArgumentException("유효하지 않은 종류입니다.");
    }
}