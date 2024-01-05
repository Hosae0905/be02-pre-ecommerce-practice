package com.example.ecommerce.member.controller;

import com.example.ecommerce.member.model.request.PostAuthenticateReq;
import com.example.ecommerce.member.model.request.PostSellerSignUpReq;
import com.example.ecommerce.member.model.request.PostSignUpReq;
import com.example.ecommerce.member.model.response.PostSignUpRes;
import com.example.ecommerce.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
@CrossOrigin("*")
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @RequestMapping(method = RequestMethod.POST, value = "/signup")
    public ResponseEntity<Object> signUp(@RequestBody PostSignUpReq postSignUpReq) {
        PostSignUpRes postSignUpRes = memberService.signUp(postSignUpReq);
        if (postSignUpRes != null) {
            return ResponseEntity.ok().body(memberService.successRes(postSignUpRes));
        } else {
            return ResponseEntity.badRequest().body("error");
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/sellersignup")
    public ResponseEntity<Object> sellerSignUp(@RequestBody PostSellerSignUpReq postSellerSignUpReq) {
        memberService.sellerSignUp(postSellerSignUpReq);
        return ResponseEntity.ok().body("ok");
    }

    @RequestMapping(method = RequestMethod.POST, value = "/authenticate")
    public ResponseEntity<Object> authenticate(@RequestBody PostAuthenticateReq postAuthenticateReq) {
        return ResponseEntity.ok().body(memberService.authenticate(postAuthenticateReq));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/checkemail")
    public ResponseEntity<Object> checkEmail(String email) {
        if(memberService.checkEmail(email)) {
            return ResponseEntity.ok().body("ok");
        } else {
            return ResponseEntity.badRequest().body("error");
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/confirm")
    public RedirectView confirm(String email, String uuid, String jwt) {
        return memberService.confirm(email, uuid, jwt);
    }

}
