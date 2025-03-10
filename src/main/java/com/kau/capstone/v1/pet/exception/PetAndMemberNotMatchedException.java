package com.kau.capstone.v1.pet.exception;

import com.kau.capstone._core.exception.ApiException;
import org.springframework.http.HttpStatus;

public class PetAndMemberNotMatchedException extends ApiException {

    private static final String message = "사용자와 매칭되는 반려동물이 없습니다.";

    public PetAndMemberNotMatchedException() {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
