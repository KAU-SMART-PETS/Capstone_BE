package com.kau.capstone.global.common;

import com.kau.capstone.global.exception.ErrorCode;
import com.kau.capstone.global.exception.NotExistPetTypeException;

public enum PetType {
    CAT(1), DOG(2);

    private final Integer value;

    PetType(int value) {
        this.value = value;
    }

    public static PetType fromInt(int value) {
        if (value == 1) {
            return CAT;
        } else if (value == 2) {
            return DOG;
        }

        throw new NotExistPetTypeException(ErrorCode.NOT_EXIST_PET_TYPE);
    }
}