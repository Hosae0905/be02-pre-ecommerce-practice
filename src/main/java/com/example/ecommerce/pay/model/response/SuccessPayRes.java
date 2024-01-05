package com.example.ecommerce.pay.model.response;

import com.example.ecommerce.cart.model.response.GetCartListRes;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class SuccessPayRes {
    private Boolean isSuccess;
    private Integer code;
    private String message;
    private Boolean success;
}
