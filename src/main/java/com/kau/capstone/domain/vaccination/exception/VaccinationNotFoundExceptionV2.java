package com.kau.capstone.domain.vaccination.exception;

import com.kau.capstone._core.exception.ApiException;
import org.springframework.http.HttpStatus;

public class VaccinationNotFoundExceptionV2 extends ApiException {

    private static final String message = "보건 정보를 찾을 수 없습니다.";

    public VaccinationNotFoundExceptionV2() {
        super(HttpStatus.NOT_FOUND, message);
    }
}
