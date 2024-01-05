package com.example.ecommerce.cart.model.response;

import com.example.ecommerce.cart.model.entity.Cart;
import com.example.ecommerce.product.model.entity.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class GetCartListRes {
    private Long idx;
    private Long productIdx;
    private Integer brandIdx;
    private Integer amount;
    private String name;
    private Integer price;
    private Integer salePrice;
    private String deliveryType;
    private String isTodayDeal;
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
