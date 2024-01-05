package com.example.ecommerce.pay.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostCompleteReq {
    private Long orderIdxs;
    private String impUid;
    private String merchantUid;
}
