package com.kau.capstone.domain.pet.exception;

import com.kau.capstone.global.exception.ApplicationException;
import com.kau.capstone.global.exception.ErrorCode;

public class PetNotFoundException extends ApplicationException {

    public PetNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }

    public PetNotFoundException(String message) {
        super(message);
    }
}
