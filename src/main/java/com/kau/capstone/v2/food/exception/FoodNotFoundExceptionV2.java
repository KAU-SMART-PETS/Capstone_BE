package com.kau.capstone.v2.food.exception;

import com.kau.capstone._core.exception.ApiException;
import org.springframework.http.HttpStatus;

public class FoodNotFoundExceptionV2 extends ApiException {
    private static final String message = "해당 사료를 찾을 수 없습니다.";

    public FoodNotFoundExceptionV2() {
        super(HttpStatus.NOT_FOUND, message);
    }
}
