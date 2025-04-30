package com.kau.capstone.v1.food.exception;

import com.kau.capstone._core.exception.ApiException;
import com.kau.capstone.global.exception.ApplicationException;
import com.kau.capstone.global.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public class FoodNotFoundException extends ApiException {
    private static final String message = "해당 사료를 찾을 수 없습니다.";

    public FoodNotFoundException() {
        super(HttpStatus.NOT_FOUND, message);
    }
}
