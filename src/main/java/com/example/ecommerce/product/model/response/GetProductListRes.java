package com.example.ecommerce.product.model.response;

import com.example.ecommerce.product.model.entity.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
public class GetProductListRes {
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
    private String filename;
    @NotNull
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
