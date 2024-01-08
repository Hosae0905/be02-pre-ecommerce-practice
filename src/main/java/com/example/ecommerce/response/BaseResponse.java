package com.example.ecommerce.response;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseResponse<T> {
    private Boolean isSuccess;
    private Integer code;
    private String message;
    private T result;
    private Boolean success;

//    public T successResponse() {
//
//    }
}
