package com.example.ecommerce.pay.controller;

import com.example.ecommerce.pay.model.request.PostCompleteReq;
import com.example.ecommerce.pay.service.PayService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pay")
@RequiredArgsConstructor
@Slf4j
public class PayController {

    private final PayService payService;

    @RequestMapping(method = RequestMethod.POST, value = "/complete")
    public ResponseEntity<Object> complete(
            @RequestBody PostCompleteReq postCompleteReq,
            @RequestHeader(name = "Authorization") String token) {
        return ResponseEntity.ok().body(payService.complete(postCompleteReq, token));
    }
}
