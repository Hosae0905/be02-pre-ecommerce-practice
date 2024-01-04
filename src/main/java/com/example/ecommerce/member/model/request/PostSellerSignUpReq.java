package com.example.ecommerce.member.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostSellerSignUpReq {
    private String email;
    private String nickName;
    private String password;
}
