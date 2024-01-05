package com.example.ecommerce.cart.model.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class GetCartInReq {
    @NotNull
    @Min(value = 0)
    private Integer amount;
    @NotNull
    private Long productIdx;
}
