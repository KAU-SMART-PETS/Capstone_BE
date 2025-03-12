package com.kau.capstone.v1.member.exception;

import com.kau.capstone._core.exception.ApiException;
import org.springframework.http.HttpStatus;

public class MemberNotFoundException extends ApiException {

    private static String message = "회원 정보를 찾을 수 없습니다.";

    public MemberNotFoundException() {
        super(HttpStatus.NOT_FOUND, message);
    }
}
