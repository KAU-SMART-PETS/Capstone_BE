package com.kau.capstone.domain.food.exception;

import com.kau.capstone.global.exception.ApplicationException;
import com.kau.capstone.global.exception.ErrorCode;

public class FoodNotFoundException extends ApplicationException {

    public FoodNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
