package com.example.ecommerce.member.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Builder
@Getter
@Setter
public class SuccessSignUpRes {
    private Boolean isSuccess;
    private Integer code;
    private String message;
    private Map<String, Object> result;
    private Boolean success;

    public static SuccessSignUpRes buildDto(Map<String, Object> result) {
        return SuccessSignUpRes.builder()
                .isSuccess(true)
                .code(1000)
                .message("요청 성공")
                .result(result)
                .success(true)
                .build();
    }
}
