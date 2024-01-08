package com.example.ecommerce.product.repository.querydsl;

import com.example.ecommerce.product.model.entity.Product;
import com.example.ecommerce.product.model.entity.QImage;
import com.example.ecommerce.product.model.entity.QProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;
import java.util.stream.Collectors;

public class ProductRepositoryCustomImpl extends QuerydslRepositorySupport implements ProductRepositoryCustom {

    public ProductRepositoryCustomImpl() {
        super(Product.class);       // 내가 조회할 엔티티 클래스를 지정해준다.
    }

    @Override
    public Page<Product> findList(Pageable pageable) {
        QProduct product = new QProduct("product");
        QImage image = new QImage("image");

        List<Product> result = from(product)
                .leftJoin(product.imageList, image).fetchJoin()     // fetchJoin을 써야지 N + 1 문제가 해결된다.
                .distinct()
                .offset(pageable.getOffset())       // 어디서 부터
                .limit(pageable.getPageSize())      // 어디까지
                .fetch().stream().collect(Collectors.toList());      // stream으로 중복 제거하기


        return new PageImpl<>(result, pageable, result.size());
    }
}
