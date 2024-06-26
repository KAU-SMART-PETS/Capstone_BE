package com.kau.capstone.global.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    // MEMBER
    ALREADY_EXIST_ID_EXCEPTION(HttpStatus.BAD_REQUEST, "이미 사용중인 아이디입니다."),
    NOT_EXIST_PET_TYPE(HttpStatus.BAD_REQUEST, "존재하지 않는 반려동물 종류입니다."),
    INVALID_GENDER(HttpStatus.BAD_REQUEST, "유효하지 않은 성별입니다."),
    LOGIN_MEMBER_NOT_FOUND(HttpStatus.UNAUTHORIZED, "로그인 되지 않았거나, 존재하지 않는 유저로 로그인중입니다."),
    SOCIAL_SITE_NOT_SUPPORTED(HttpStatus.NOT_FOUND, "지원하지 않는 소셜 로그인 사이트입니다."),
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 회원입니다.")
    ;//Error Code를 작성한 마지막에 ;를 추가합니다.

    private final HttpStatus httpStatus;
    private final String simpleMessage;

    ErrorCode(HttpStatus httpStatus, String simpleMessage) {
        this.httpStatus = httpStatus;
        this.simpleMessage = simpleMessage;
    }
}