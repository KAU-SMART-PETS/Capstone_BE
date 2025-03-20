package com.kau.capstone.v2.reward.exception;

import com.kau.capstone._core.exception.ApiException;
import org.springframework.http.HttpStatus;

public class RewardNotFoundExceptionV2 extends ApiException {

    private static final String message = "요청하신 ID에 맞는 리워드 정보를 찾을 수 업습니다.";

    public RewardNotFoundExceptionV2() {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
