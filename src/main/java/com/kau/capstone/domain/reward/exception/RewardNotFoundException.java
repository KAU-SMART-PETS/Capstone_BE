package com.kau.capstone.domain.reward.exception;

import com.kau.capstone.global.exception.ApplicationException;
import com.kau.capstone.global.exception.ErrorCode;

public class RewardNotFoundException extends ApplicationException {

    public RewardNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
