package com.kau.capstone.domain.member.exception;

import com.kau.capstone.global.exception.ApplicationException;
import com.kau.capstone.global.exception.ErrorCode;

public class PointNotEnoughException extends ApplicationException {

    public PointNotEnoughException(ErrorCode errorCode) {
        super(errorCode);
    }
}
