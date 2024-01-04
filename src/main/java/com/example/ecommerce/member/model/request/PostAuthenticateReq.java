package com.example.ecommerce.member.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostAuthenticateReq {
    private String username;
    private String password;
}
