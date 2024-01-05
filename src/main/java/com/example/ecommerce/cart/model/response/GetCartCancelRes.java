package com.example.ecommerce.cart.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Builder
@Getter
@Setter
public class GetCartCancelRes {
    @NotNull
    private Long idx;
    @NotNull
    private Boolean status;
}
