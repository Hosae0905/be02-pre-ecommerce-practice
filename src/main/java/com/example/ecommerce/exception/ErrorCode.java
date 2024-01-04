package com.example.ecommerce.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    DUPLICATE_USER(HttpStatus.CONFLICT, "이미 존재하는 사용자입니다.", 7000);

    private final HttpStatus httpStatus;
    private final String message;
    private final Integer errorNumber;
}
