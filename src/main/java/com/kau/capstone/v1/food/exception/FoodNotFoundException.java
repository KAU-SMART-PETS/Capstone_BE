package com.kau.capstone.v1.food.exception;

import com.kau.capstone.global.exception.ApplicationException;
import com.kau.capstone.global.exception.ErrorCode;

public class FoodNotFoundException extends ApplicationException {

    public FoodNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
