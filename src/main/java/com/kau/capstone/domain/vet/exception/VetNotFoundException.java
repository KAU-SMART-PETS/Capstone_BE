package com.kau.capstone.domain.vet.exception;

import com.kau.capstone.global.exception.ApplicationException;
import com.kau.capstone.global.exception.ErrorCode;

public class VetNotFoundException extends ApplicationException {

    public VetNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
