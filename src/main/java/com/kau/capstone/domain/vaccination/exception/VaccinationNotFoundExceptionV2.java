package com.kau.capstone.domain.vaccination.exception;

import com.kau.capstone._core.exception.ApiException;
import org.springframework.http.HttpStatus;

public class VaccinationNotFoundExceptionV2 extends ApiException {

    private static final String message = "존재하지 않은 보건정보입니다.";

    public VaccinationNotFoundExceptionV2() {
        super(HttpStatus.NOT_FOUND, message);
    }
}
