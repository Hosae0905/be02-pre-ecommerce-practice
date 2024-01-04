package com.example.ecommerce.member.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostEmailConfirmReq {
    private String email;
    private String uuid;
    private String jwt;
}
