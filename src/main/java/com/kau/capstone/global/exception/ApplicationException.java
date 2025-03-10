package com.kau.capstone.global.exception;

import lombok.Getter;

@Getter
public class ApplicationException extends RuntimeException {

    private final ErrorCode errorCode;

    public ApplicationException(ErrorCode errorCode) {
        super(errorCode.getSimpleMessage());
        this.errorCode = errorCode;
    }

    public ApplicationException(String message) {
        super(message);
        this.errorCode = null;
    }

}