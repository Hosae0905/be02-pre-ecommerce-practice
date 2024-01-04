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
}
