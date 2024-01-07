package com.example.ecommerce.product.controller;

import com.example.ecommerce.product.model.request.PostProductReq;
import com.example.ecommerce.product.model.response.PostCreateRes;
import com.example.ecommerce.product.model.response.SuccessCreateRes;
import com.example.ecommerce.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;

    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public ResponseEntity<Object> create(
            @RequestPart PostProductReq postProductReq,
            @RequestPart MultipartFile[] uploadFiles) {
        PostCreateRes postCreateRes = productService.create(postProductReq);
        SuccessCreateRes success = productService.saveImage(uploadFiles, postCreateRes.getIdx());
        return ResponseEntity.ok().body(success);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public ResponseEntity<Object> list() {
        return ResponseEntity.ok().body(productService.list());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{idx}")
    public ResponseEntity<Object> select(@PathVariable Long idx) {
        return ResponseEntity.ok().body(productService.selectProduct(idx));
    }
}
