package com.example.ecommerce.product.model.entity;

import com.example.ecommerce.product.model.request.PostProductReq;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer categoryIdx;
    private Integer price;
    private Integer salePrice;
    private String deliveryType;
    private String isTodayDeal;
    private String contents;

    public static Product dtoToEntity(PostProductReq postProductReq) {
        return Product.builder()
                .name(postProductReq.getName())
                .categoryIdx(postProductReq.getCategoryIdx())
                .price(postProductReq.getPrice())
                .salePrice(postProductReq.getSalePrice())
                .deliveryType(postProductReq.getDeliveryType())
                .isTodayDeal(postProductReq.getIsTodayDeal())
                .contents(postProductReq.getContents())
                .build();
    }
}
