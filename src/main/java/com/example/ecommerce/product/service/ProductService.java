package com.example.ecommerce.product.service;

import com.example.ecommerce.product.model.response.*;
import com.example.ecommerce.product.model.entity.Image;
import com.example.ecommerce.product.model.entity.Product;
import com.example.ecommerce.product.model.request.PostProductReq;
import com.example.ecommerce.product.repository.ImageRepository;
import com.example.ecommerce.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;
    private final FileSaveService fileSaveService;
    private final ImageRepository imageRepository;

    @Transactional
    public SuccessCreateRes create(PostProductReq postProductReq, MultipartFile[] uploadFiles) {

        log.info("postProductReq {}", postProductReq);

        PostCreateRes postCreateRes = PostCreateRes.buildDto(productRepository.save(Product.dtoToEntity(postProductReq)));

        for (MultipartFile uploadFile : uploadFiles) {
            String saveFile = fileSaveService.saveFile(uploadFile);
            imageRepository.save(Image.buildEntity(saveFile, postCreateRes.getIdx()));
        }

        return SuccessCreateRes.successDto(postCreateRes.getIdx());
    }

    @Transactional(readOnly = true)
    public SuccessListRes list() {
        List<Product> products = productRepository.findAllQuery();
        List<GetProductListRes> productList = new ArrayList<>();

        if (products != null) {
            for (Product product : products) {
                productList.add(GetProductListRes.entityToDto(product));
            }

            return SuccessListRes.successDto(productList);
        } else {
            return null;
        }
    }

    // Querydsl 처리
//    @Transactional(readOnly = true)
//    public SuccessListRes list(Integer page, Integer size) {
//        Pageable pageable = PageRequest.of(page - 1, size);
//        Page<Product> products = productRepository.findList(pageable);
//
//        List<GetProductListRes> productList = new ArrayList<>();
//
//        if (products != null) {
//            for (Product product : products) {
//                productList.add(GetProductListRes.entityToDto(product));
//            }
//
//            return SuccessListRes.successDto(productList);
//        } else {
//            return null;
//        }
//    }

    public SuccessSelectRes selectProduct(Long id) {
        Optional<Product> product = productRepository.findById(id);

        if (product.isPresent()) {
            Product productInfo = product.get();
            String images = "";
            for (Image image : productInfo.getImageList()) {
                images = image.getImage() + ",";
            }
            return SuccessSelectRes.successDto(GetProductInfoRes.createDto(productInfo, images));
        } else {
            return null;
        }
    }
}
