package com.kau.capstone.global.common;

import com.kau.capstone.global.exception.ErrorCode;
import com.kau.capstone.global.exception.InvalidGenderException;

public enum Gender {
    MALE(1), FEMALE(2);

    private final Integer value;

    Gender(int value) {
        this.value = value;
    }

    public static Gender fromInt(int value) {
        if (value == 1) {
            return MALE;
        } else if (value == 2) {
            return FEMALE;
        }

        throw new InvalidGenderException(ErrorCode.INVALID_GENDER);

    }
}
