package com.example.ecommerce.likes.service;

import com.example.ecommerce.member.model.entity.Member;
import com.example.ecommerce.product.model.entity.Product;
import com.example.ecommerce.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.StaleStateException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final ProductRepository productRepository;

//    @Transactional      // 이것만 달았다고 격리성이 보장되는게 아니다. 다른 작업이 추가적으로 필요함
//    public void likes(Member member, Long id) throws StaleStateException {
//        Optional<Product> result = productRepository.findById(id);
//
//        if (result.isPresent()) {
//            Product product = result.get();
////            product.increaseLikeCount();
//            productRepository.save(product);
//        }
//    }
}
