package com.kau.capstone.v2.reward.exception;

import com.kau.capstone._core.exception.ApiException;
import org.springframework.http.HttpStatus;

public class DeletedRewardExceptionV2 extends ApiException {

    private static final String message = "삭제된 리워드는 수정할 수 없습니다.";

    public DeletedRewardExceptionV2() {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
