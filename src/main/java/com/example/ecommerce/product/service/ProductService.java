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

//    public SuccessCreateRes saveImage(MultipartFile[] uploadFiles, Long idx) {
//        List<Image> images = new ArrayList<>();
//        for (MultipartFile uploadFile : uploadFiles) {
//            String saveFile = fileSaveService.saveFile(uploadFile);
//            Image image = imageRepository.save(Image.buildEntity(saveFile, idx));
//            images.add(image);
//        }
//
//        Optional<Product> product = productRepository.findById(idx);
//
//        if (product.isPresent()) {
//            product.get().setImageList(images);
//            productRepository.save(product.get());
//        }
//
//        return SuccessCreateRes.successDto(idx);
//    }

    @Transactional(readOnly = true)
    public SuccessListRes list() {
//        List<Product> products = productRepository.findAll();     // N + 1 문제 발생
        List<Product> products = productRepository.findAllQuery();  // jqpl로 N + 1 문제 해결
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

    // Pageable 처리
//    public SuccessListRes list(Integer page, Integer size) {
//        List<Product> products = productRepository.findAllQuery();  // jqpl로 N + 1 문제 해결
//
//        Pageable pageable = PageRequest.of(page - 1, size);
//        Page<Product> result = productRepository.findAll(pageable);
//
//        List<GetProductListRes> productList = new ArrayList<>();
//
//        if (products != null) {
//            for (Product product : result.getContent()) {
//                productList.add(GetProductListRes.entityToDto(product));
//            }
//
//            return SuccessListRes.successDto(productList);
//        } else {
//            return null;
//        }
//    }

    // JPQL로 처리
//    public SuccessListRes list(Integer page, Integer size) {
//        List<Product> products = productRepository.findAllQueryWithPage(page, size);  // jqpl로 N + 1 문제 해결
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

    // Querydsl 처리
    @Transactional(readOnly = true)
    public SuccessListRes list(Integer page, Integer size) {
//        List<Product> products = productRepository.findAllQueryWithPage(page, size);  // jqpl로 N + 1 문제 해결
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Product> products = productRepository.findList(pageable);

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
