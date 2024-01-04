package com.example.ecommerce.product.service;

import com.example.ecommerce.product.model.response.*;
import com.example.ecommerce.product.model.entity.Image;
import com.example.ecommerce.product.model.entity.Product;
import com.example.ecommerce.product.model.request.PostProductReq;
import com.example.ecommerce.product.repository.ImageRepository;
import com.example.ecommerce.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final FileSaveService fileSaveService;
    private final ImageRepository imageRepository;

    // TODO: 상품 등록
    public PostCreateRes create(PostProductReq postProductReq) {
        Product product = productRepository.save(Product.dtoToEntity(postProductReq));

        return PostCreateRes.builder().idx(product.getId()).build();
    }

    // TODO: 상품 이미지 저장
    public SuccessCreateRes saveImage(MultipartFile[] uploadFiles, Long idx) {
        List<Image> images = new ArrayList<>();
        for (MultipartFile uploadFile : uploadFiles) {
            String saveFile = fileSaveService.saveFile(uploadFile);
            Image image = imageRepository.save(Image.builder()
                    .image(saveFile)
                    .product(Product.builder().id(idx).build())
                    .build());
            images.add(image);
        }

        Optional<Product> product = productRepository.findById(idx);

        if (product.isPresent()) {
            product.get().setImageList(images);
            productRepository.save(product.get());
        }

        return SuccessCreateRes.builder()
                .isSuccess(true)
                .code(1000)
                .message("요청 성공")
                .result(PostCreateRes.builder().idx(idx).build())
                .build();
    }

    // TODO: 상품 목록 조회(전체)
    public SuccessListRes list() {
        List<Product> products = productRepository.findAll();
        List<GetProductListRes> productList = new ArrayList<>();

        if (products != null) {
            for (Product product : products) {
                productList.add(GetProductListRes.builder()
                        .idx(product.getId())
                        .name(product.getName())
                        .brandIdx(1)
                        .categoryIdx(1L)
                        .price(product.getPrice())
                        .salePrice(product.getSalePrice())
                        .deliveryType(product.getDeliveryType())
                        .isTodayDeal(product.getIsTodayDeal())
                        .filename(product.getImageList().get(0).getImage())
                        .like_check(false)
                        .build());
            }

            return SuccessListRes.builder()
                    .isSuccess(true)
                    .code(1000)
                    .message("요청 성공")
                    .result(productList)
                    .success(true)
                    .build();
        } else {
            return null;
        }
    }

    // TODO: 상품 목록 조회(하나만)
    public SuccessSelectRes selectProduct(Long id) {
        Optional<Product> product = productRepository.findById(id);

        if (product.isPresent()) {
            Product productInfo = product.get();
            String images = "";
            for (Image image : productInfo.getImageList()) {
                images = image + ",";
            }
            GetProductInfoRes getProductInfoRes = GetProductInfoRes.builder()
                    .idx(productInfo.getId())
                    .name(productInfo.getName())
                    .brandIdx(1)
                    .categoryIdx(1L)
                    .price(productInfo.getPrice())
                    .salePrice(productInfo.getSalePrice())
                    .deliveryType(productInfo.getDeliveryType())
                    .isTodayDeal(productInfo.getIsTodayDeal())
                    .contents(productInfo.getContents())
                    .filename(images)
                    .likeCount(0)
                    .build();

            return SuccessSelectRes.builder()
                    .isSuccess(true)
                    .code(1000)
                    .result(getProductInfoRes)
                    .message("요청 성공")
                    .success(true)
                    .build();
        } else {
            return null;
        }
    }
}
