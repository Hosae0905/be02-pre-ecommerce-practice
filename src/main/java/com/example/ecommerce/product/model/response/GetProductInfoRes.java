package com.example.ecommerce.product.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class GetProductInfoRes {
    private Long idx;
    private String name;
    private Integer brandIdx;
    private Long categoryIdx;
    private Integer price;
    private Integer salePrice;
    private String deliveryType;
    private String isTodayDeal;
    private String contents;
    private String filename;
    private Integer likeCount;
}
