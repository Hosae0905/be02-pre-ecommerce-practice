package com.example.ecommerce.product.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@Builder
public class SuccessResponse {
    private Boolean isSuccess;
    private Integer code;
    private String message;
    private Map<String, Object> result;
    private Boolean success;
}
