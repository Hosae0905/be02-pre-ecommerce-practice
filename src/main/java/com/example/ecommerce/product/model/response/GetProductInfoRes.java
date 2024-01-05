package com.example.ecommerce.product.model.response;

import com.example.ecommerce.product.model.entity.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Builder
@Getter
@Setter
public class GetProductInfoRes {
    @NotNull
    private Long idx;
    @NotNull
    @Size(max = 100)
    private String name;
    @NotNull
    private Integer brandIdx;
    @NotNull
    private Long categoryIdx;
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
    @NotNull
    private String filename;
    @NotNull
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
