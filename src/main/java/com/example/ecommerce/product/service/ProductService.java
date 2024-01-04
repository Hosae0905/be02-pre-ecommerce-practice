package com.example.ecommerce.product.service;

import com.example.ecommerce.product.model.SuccessResponse;
import com.example.ecommerce.product.model.entity.Product;
import com.example.ecommerce.product.model.request.PostProductReq;
import com.example.ecommerce.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    // TODO: 상품 등록
    public void create(PostProductReq postProductReq) {
        productRepository.save(Product.dtoToEntity(postProductReq));
    }

    // TODO: 상품 이미지 저장
    public void saveImage(MultipartFile[] uploadFiles) {

    }

    // TODO: 상품 목록 조회(전체)
    public void list() {

    }

    // TODO: 상품 목록 조회(하나만)
    public SuccessResponse selectProdcut(Long idx) {
        Optional<Product> productInfo = productRepository.findById(idx);

        if (productInfo.isPresent()) {
            Product product = productInfo.get();
            Map<String, Object> result = new HashMap<>();

            result.put("idx", product.getId());
            result.put("name", product.getName());
            result.put("categoryIdx", 1);
            result.put("price", product.getPrice());
            result.put("salePrice", product.getSalePrice());
            result.put("deliveryType", product.getDeliveryType());
            result.put("isTodayDeal", "n");
            result.put("contents", product.getContents());
            result.put("filename", 1);
            result.put("likeCount", 0);


            return SuccessResponse.builder()
                    // 응답 성공 메세지 추가
                    .build();
        } else {
            return null;
        }
    }
}
