package com.kau.capstone.global.common.s3.exception;

import com.kau.capstone._core.exception.ApiException;
import org.springframework.http.HttpStatus;

public class FileIOException extends ApiException {

    private static final String message = "inputstream io exception";

    public FileIOException() {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
