package com.kau.capstone.global.common.s3.exception;

import com.kau.capstone.global.exception.ApplicationException;
import com.kau.capstone.global.exception.ErrorCode;

public class CannotConvertFileException extends ApplicationException {

    public CannotConvertFileException(ErrorCode errorCode) {
        super(errorCode);
    }
}
