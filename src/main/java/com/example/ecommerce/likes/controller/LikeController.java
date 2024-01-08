package com.example.ecommerce.likes.controller;

import com.example.ecommerce.likes.service.LikeService;
import com.example.ecommerce.member.model.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/likes")
@RequiredArgsConstructor
@Slf4j
public class LikeController {

    private final LikeService likeService;

//    @RequestMapping(method = RequestMethod.GET, value = "/{idx}")
//    public ResponseEntity<Object> likes(@AuthenticationPrincipal Member member, @PathVariable Long idx) {
//        try {
//            likeService.likes(member, idx);
//            return ResponseEntity.ok().body("ok");
//        } catch (Exception e) {
//            log.info("동시성 문제 발생");
//            likeService.likes(member, idx);
//            return ResponseEntity.badRequest().body("error");
//        }
//    }
}
