package com.kau.capstone.global.common.s3.exception;

import com.kau.capstone._core.exception.ApiException;
import org.springframework.http.HttpStatus;

public class FileNotExistException extends ApiException {

    private static final String message = "삭제할 파일을 찾을 수 없습니다.";

    public FileNotExistException() {
        super(HttpStatus.NOT_FOUND, message);
    }
}
