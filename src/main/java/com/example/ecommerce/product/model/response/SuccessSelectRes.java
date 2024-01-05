package com.example.ecommerce.product.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SuccessSelectRes {
    private Boolean isSuccess;
    private Integer code;
    private String message;
    private GetProductInfoRes result;
    private Boolean success;

    public static SuccessSelectRes successDto(GetProductInfoRes getProductInfoRes) {
        return SuccessSelectRes.builder()
                .isSuccess(true)
                .code(1000)
                .result(getProductInfoRes)
                .message("요청 성공")
                .success(true)
                .build();
    }
}
