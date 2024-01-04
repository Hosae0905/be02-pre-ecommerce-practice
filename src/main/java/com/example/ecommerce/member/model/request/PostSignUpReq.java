package com.example.ecommerce.member.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostSignUpReq {
    private String email;
    private String nickname;
    private String password;
}
