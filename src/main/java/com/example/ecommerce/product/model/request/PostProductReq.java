package com.example.ecommerce.product.model.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class PostProductReq {
    @NotNull
    @Size(max = 100)
    private String name;
    @NotNull
    private Integer categoryIdx;
    @NotNull
    @Min(value = 0)
    private Integer price;
    @Min(value = 0)
    private Integer salePrice;
    @NotNull
    private String deliveryType;
    @NotNull
    private String isTodayDeal;
    @NotNull
    private String contents;
}
