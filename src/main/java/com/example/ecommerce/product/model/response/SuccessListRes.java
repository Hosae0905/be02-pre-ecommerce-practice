package com.example.ecommerce.product.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class SuccessListRes {
    private Boolean isSuccess;
    private Integer code;
    private String message;
    private List<GetProductListRes> result;
    private Boolean success;

    public static SuccessListRes successDto(List<GetProductListRes> productList) {
        return SuccessListRes.builder()
                .isSuccess(true)
                .code(1000)
                .message("요청 성공")
                .result(productList)
                .success(true)
                .build();
    }
}
