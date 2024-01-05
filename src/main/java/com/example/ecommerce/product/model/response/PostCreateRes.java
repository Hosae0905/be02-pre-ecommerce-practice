package com.example.ecommerce.product.model.response;

import com.example.ecommerce.product.model.entity.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class PostCreateRes {
    private Long idx;

    public static PostCreateRes buildDto(Product product) {
        return PostCreateRes.builder().idx(product.getId()).build();
    }
}
