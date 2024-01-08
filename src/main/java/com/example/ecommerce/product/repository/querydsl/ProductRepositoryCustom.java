package com.example.ecommerce.product.repository.querydsl;

import com.example.ecommerce.product.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

public interface ProductRepositoryCustom {
    public Page<Product> findList(Pageable pageable);
}
