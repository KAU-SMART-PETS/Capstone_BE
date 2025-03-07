package com.kau.capstone.domain.food.exception;

import com.kau.capstone.global.exception.ApplicationException;
import com.kau.capstone.global.exception.ErrorCode;

public class FoodNotFoundException extends ApplicationException {

    public FoodNotFoundException() {
        super(ErrorCode.FOOD_NOT_FOUND);
    }
}
