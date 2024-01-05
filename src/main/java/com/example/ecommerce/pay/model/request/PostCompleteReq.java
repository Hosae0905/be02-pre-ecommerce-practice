package com.example.ecommerce.pay.model.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PostCompleteReq {
    @NotNull
    private Long orderIdxs;
    @NotNull
    private String impUid;
    @NotNull
    private String merchantUid;
}
