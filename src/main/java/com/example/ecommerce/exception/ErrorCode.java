package com.example.ecommerce.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    // TODO: 예외 코드 추가 예정

    DUPLICATE_USER(7000, HttpStatus.CONFLICT, "이미 존재하는 사용자입니다."),
    INVALID_TOKEN(7000, HttpStatus.UNAUTHORIZED, "인증되지 않은 토큰입니다."),
    INVALID_PASSWORD(7000, HttpStatus.UNAUTHORIZED, "비밀번호가 틀립니다."),

    CREATE_USER(1000, HttpStatus.CREATED, "회원가입 성공"),

    SELECT_PRODUCT(1000, HttpStatus.OK, "상품조회 성공")

    ;

    private final Integer code;
    private final HttpStatus httpStatus;
    private final String message;
}
