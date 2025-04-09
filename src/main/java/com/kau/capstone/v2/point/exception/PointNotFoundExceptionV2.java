package com.kau.capstone.v2.point.exception;

import com.kau.capstone._core.exception.ApiException;
import org.springframework.http.HttpStatus;

public class PointNotFoundExceptionV2 extends ApiException {

    private static final String message = "포인트 정보를 찾을 수 없습니다.";

    public PointNotFoundExceptionV2() {
        super(HttpStatus.NOT_FOUND, message);
    }
}
