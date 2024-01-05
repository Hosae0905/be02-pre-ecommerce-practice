package com.example.ecommerce.cart.model.response;

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
    private List<GetCartListRes> result;
    private Boolean success;
}
