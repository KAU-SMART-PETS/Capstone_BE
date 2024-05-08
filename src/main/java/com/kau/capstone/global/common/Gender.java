package com.kau.capstone.global.common;

public enum Gender {
    MALE(1), FEMALE(2);

    private final Integer value;

    Gender(int value) {
        this.value = value;
    }

    // 정수를 ENUM으로 변환하는 메서드
    public static Gender fromInt(int value) {
        if (value == 1) {
            return MALE;
        } else if (value == 2) {
            return FEMALE;
        } else {
            throw new IllegalArgumentException("유효하지 않은 성별입니다.");
        }
    }
}
