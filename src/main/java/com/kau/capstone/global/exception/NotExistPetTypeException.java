package com.kau.capstone.global.exception;

public class NotExistPetTypeException extends ApplicationException {

    public NotExistPetTypeException(ErrorCode errorCode) {
        super(errorCode);
    }
}