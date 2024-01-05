package com.example.ecommerce.product.model.response;

import com.example.ecommerce.product.model.entity.Product;
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

    public static GetProductInfoRes createDto(Product productInfo, String images) {
        return GetProductInfoRes.builder()
                .idx(productInfo.getId())
                .name(productInfo.getName())
                .brandIdx(1)
                .categoryIdx(1L)
                .price(productInfo.getPrice())
                .salePrice(productInfo.getSalePrice())
                .deliveryType(productInfo.getDeliveryType())
                .isTodayDeal(productInfo.getIsTodayDeal())
                .contents(productInfo.getContents())
                .filename(images)
                .likeCount(0)
                .build();
    }
}
