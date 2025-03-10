package com.kau.capstone.domain.pet.exception;

import com.kau.capstone._core.exception.ApiException;
import org.springframework.http.HttpStatus;

public class PetNotFoundException extends ApiException {

    private static final String message = "반려동물 정보를 찾을 수 없습니다.";

    public PetNotFoundException() {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
