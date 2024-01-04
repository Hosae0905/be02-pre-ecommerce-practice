package com.example.ecommerce.member.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class PostSignUpRes {
    private Long id;
    private String email;
    private String accessToken;
}
