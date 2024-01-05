package com.example.ecommerce.product.model.response;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SuccessCreateRes {
    private Boolean isSuccess;
    private Integer code;
    private String message;
    private PostCreateRes result;
    private Boolean success;

    public static SuccessCreateRes successDto(Long idx) {
        return SuccessCreateRes.builder()
                .isSuccess(true)
                .code(1000)
                .message("요청 성공")
                .result(PostCreateRes.builder().idx(idx).build())
                .build();
    }
}
