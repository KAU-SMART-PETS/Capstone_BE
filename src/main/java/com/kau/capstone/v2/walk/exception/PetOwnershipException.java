package com.kau.capstone.v2.walk.exception;

import com.kau.capstone._core.exception.ApiException;
import org.springframework.http.HttpStatus;

public class PetOwnershipException extends ApiException {

    private static final String message = "해당 반려동물은 유저의 반려동물이 아닙니다.";

    // TODO(동혁) : 해당 HttpStatus 적절한지 체크
    public PetOwnershipException() {
        super(HttpStatus.NOT_FOUND, message);
    }
}
