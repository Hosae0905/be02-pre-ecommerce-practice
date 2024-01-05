package com.example.ecommerce.product.model.entity;

import com.example.ecommerce.cart.model.entity.Cart;
import com.example.ecommerce.product.model.request.PostProductReq;
import lombok.*;

import javax.persistence.*;
import java.util.List;

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

    @OneToMany(mappedBy = "product")
    private List<Image> imageList;

    @OneToMany(mappedBy = "product")
    private List<Cart> cartList;

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
