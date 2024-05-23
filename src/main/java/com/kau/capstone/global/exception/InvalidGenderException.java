package com.kau.capstone.global.exception;

public class InvalidGenderException extends ApplicationException {

    public InvalidGenderException(ErrorCode errorCode) {
        super(errorCode);
    }
}