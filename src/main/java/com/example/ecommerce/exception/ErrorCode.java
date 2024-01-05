package com.example.ecommerce.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    // TODO: 예외 코드 추가 예정

    DUPLICATE_USER(HttpStatus.CONFLICT, "이미 존재하는 사용자입니다."),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "인증되지 않은 토큰입니다."),

    CREATE_USER(HttpStatus.CREATED, "회원가입 성공"),

    SELECT_PRODUCT(HttpStatus.OK, "상품조회 성공")

    ;

    private final HttpStatus httpStatus;
    private final String message;
}
