package com.example.ecommerce.pay.service;

import com.example.ecommerce.cart.repository.CartRepository;
import com.example.ecommerce.member.model.entity.Member;
import com.example.ecommerce.member.repository.MemberRepository;
import com.example.ecommerce.pay.model.request.PostCompleteReq;
import com.example.ecommerce.pay.model.response.SuccessPayRes;
import com.example.ecommerce.pay.repository.PayRepository;
import com.example.ecommerce.util.JwtUtil;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PayService {

    private final PayRepository payRepository;
    private final IamportClient iamportClient;
    private final CartRepository cartRepository;
    private final MemberRepository memberRepository;

    @Value("${jwt.secret-key}")
    private String key;

    @Transactional
    public SuccessPayRes complete(PostCompleteReq postCompleteReq, String token) {
        String impUid = postCompleteReq.getImpUid();

        if (token != null && token.startsWith("Bearer ")) {
            token = token.split(" ")[1];
            Long id = JwtUtil.getUserId(token, key);

            try {
                IamportResponse<Payment> response = iamportClient.paymentByImpUid(impUid);
                Optional<Member> member = memberRepository.findById(id);

                if (member.isPresent()) {
                    cartRepository.deleteAllByMember(Member.builder().id(id).build());
                }

                return SuccessPayRes.builder()
                        .isSuccess(true)
                        .code(1000)
                        .message(String.valueOf(response.getResponse().getAmount()))
                        .build();

            } catch (IamportResponseException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            return null;
        }
    }
}
