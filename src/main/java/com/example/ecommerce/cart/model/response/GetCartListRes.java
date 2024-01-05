package com.example.ecommerce.cart.model.response;

import com.example.ecommerce.cart.model.entity.Cart;
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
public class GetCartListRes {
    @NotNull
    private Long idx;
    @NotNull
    private Long productIdx;
    @NotNull
    private Integer brandIdx;
    @NotNull
    @Min(value = 0)
    private Integer amount;
    @NotNull
    @Size(max = 20)
    private String name;
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

    public static GetCartListRes buildDto(Cart cart, Product product) {
        return GetCartListRes.builder()
                .idx(cart.getId())
                .productIdx(product.getId())
                .brandIdx(1)
                .amount(cart.getAmount())
                .name(product.getName())
                .price(product.getPrice())
                .salePrice(product.getSalePrice())
                .deliveryType(product.getDeliveryType())
                .isTodayDeal(product.getIsTodayDeal())
                .filename(product.getImageList().get(0).getImage())
                .build();
    }
}
