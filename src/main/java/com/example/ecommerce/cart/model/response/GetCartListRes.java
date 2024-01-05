package com.example.ecommerce.cart.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
/**
 *              "idx": 4,			// 장바구니의 IDX
 *             "productIdx": 1,
 *             "brandIdx": 7,
 *             "amount": 1,
 *             "name": "상품01",
 *             "price": 10000,
 *             "salePrice": 9000,
 *             "deliveryType": "무료",
 *             "isTodayDeal": "n",
 *             "filename": "common.jpeg"
 */

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
}
