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
}
