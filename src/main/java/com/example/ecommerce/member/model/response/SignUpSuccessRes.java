package com.example.ecommerce.member.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Builder
@Getter
@Setter
public class SignUpSuccessRes {
    private Boolean isSuccess;
    private Integer code;
    private String message;
    private Map<String, Object> result;
    private Boolean success;
}
