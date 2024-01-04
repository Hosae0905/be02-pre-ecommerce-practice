package com.example.ecommerce.product.controller;

import com.example.ecommerce.product.model.request.PostProductReq;
import com.example.ecommerce.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public ResponseEntity<Object> create(
            @RequestPart PostProductReq postProductReq,
            @RequestPart MultipartFile[] uploadFiles) {
        productService.create(postProductReq);
        productService.saveImage(uploadFiles);

        return ResponseEntity.ok().body("ok");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public ResponseEntity<Object> list() {
        productService.list();
        return ResponseEntity.ok().body("ok");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{idx}")
    public ResponseEntity<Object> select(@PathVariable Long idx) {
        return ResponseEntity.ok().body(productService.selectProdcut(idx));
    }


}
