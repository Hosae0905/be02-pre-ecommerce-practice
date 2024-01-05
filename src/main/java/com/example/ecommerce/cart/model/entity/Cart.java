package com.example.ecommerce.cart.model.entity;

import com.example.ecommerce.member.model.entity.Member;
import com.example.ecommerce.product.model.entity.Product;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer amount;

    @ManyToOne
    @JoinColumn(name = "Member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "Product_id")
    private Product product;
}
