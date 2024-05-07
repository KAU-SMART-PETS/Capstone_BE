package com.kau.capstone.global.exception;

public class ForbiddenException extends ApplicationException{

    public ForbiddenException(ErrorCode errorCode) {
        super(errorCode);
    }
}
