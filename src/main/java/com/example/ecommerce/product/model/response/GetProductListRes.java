package com.example.ecommerce.product.model.response;

import com.example.ecommerce.product.model.entity.Product;
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

    public static GetProductListRes entityToDto(Product product) {
        return GetProductListRes.builder()
                .idx(product.getId())
                .name(product.getName())
                .brandIdx(1)
                .categoryIdx(1L)
                .price(product.getPrice())
                .salePrice(product.getSalePrice())
                .deliveryType(product.getDeliveryType())
                .isTodayDeal(product.getIsTodayDeal())
                .filename(product.getImageList().get(0).getImage())
                .like_check(false)
                .build();
    }
}
