package com.kau.capstone.v2.pet.exception;

import com.kau.capstone._core.exception.ApiException;
import org.springframework.http.HttpStatus;

public class PetNotFoundExceptionV2 extends ApiException {

    private static final String message = "반려동물 정보를 찾을 수 없습니다.";

    public PetNotFoundExceptionV2() {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
