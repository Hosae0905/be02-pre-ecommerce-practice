package com.example.ecommerce.cart.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class GetCartCancelRes {
    private Long idx;
    private Boolean status;
}
