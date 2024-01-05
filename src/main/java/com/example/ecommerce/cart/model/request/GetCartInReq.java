package com.example.ecommerce.cart.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetCartInReq {
    private Integer amount;
    private Long productIdx;
}
