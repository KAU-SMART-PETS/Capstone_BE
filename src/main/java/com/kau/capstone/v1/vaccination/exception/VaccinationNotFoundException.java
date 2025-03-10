package com.kau.capstone.v1.vaccination.exception;

import com.kau.capstone.global.exception.ApplicationException;
import com.kau.capstone.global.exception.ErrorCode;

public class VaccinationNotFoundException extends ApplicationException {

    public VaccinationNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
