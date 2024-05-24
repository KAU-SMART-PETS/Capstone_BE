package com.kau.capstone.domain.auth.exception;

import com.kau.capstone.global.exception.ApplicationException;
import com.kau.capstone.global.exception.ErrorCode;

public class AuthException extends ApplicationException {

    public AuthException(ErrorCode errorCode) {
        super(errorCode);
    }
}
