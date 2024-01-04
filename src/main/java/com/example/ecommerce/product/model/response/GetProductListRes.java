package com.example.ecommerce.product.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GetProductListRes {
    private Long idx;
    private String name;
    private Integer brandIdx;
    private Long categoryIdx;
    private Integer price;
    private Integer salePrice;
    private String deliveryType;
    private String isTodayDeal;
    private String filename;
    private Boolean like_check;
}
