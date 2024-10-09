package com.kau.capstone.domain.hospital.exception;

import com.kau.capstone.global.exception.ApplicationException;
import com.kau.capstone.global.exception.ErrorCode;

public class HospitalNotFoundException extends ApplicationException {

    public HospitalNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
