package com.example.ecommerce.cart.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class SuccessCancelRes {
    private Boolean isSuccess;
    private Integer code;
    private String message;
    private GetCartCancelRes result;
    private Boolean success;
}
