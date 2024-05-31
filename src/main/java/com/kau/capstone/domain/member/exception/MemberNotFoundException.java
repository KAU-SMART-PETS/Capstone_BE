package com.kau.capstone.domain.member.exception;

import com.kau.capstone.global.exception.ApplicationException;
import com.kau.capstone.global.exception.ErrorCode;

public class MemberNotFoundException extends ApplicationException {

    public MemberNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
