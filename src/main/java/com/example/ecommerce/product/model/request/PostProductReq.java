package com.example.ecommerce.product.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostProductReq {
    private String name;
    private Integer categoryIdx;
    private Integer price;
    private Integer salePrice;
    private String deliveryType;
    private String isTodayDeal;
    private String contents;
}
