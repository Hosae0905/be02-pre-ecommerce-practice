package com.example.ecommerce.cart.service;

import com.example.ecommerce.cart.model.entity.Cart;
import com.example.ecommerce.cart.model.request.GetCartInReq;
import com.example.ecommerce.cart.model.response.GetCartCancelRes;
import com.example.ecommerce.cart.model.response.GetCartListRes;
import com.example.ecommerce.cart.model.response.SuccessCancelRes;
import com.example.ecommerce.cart.model.response.SuccessListRes;
import com.example.ecommerce.cart.repository.CartRepository;
import com.example.ecommerce.member.model.entity.Member;
import com.example.ecommerce.member.repository.MemberRepository;
import com.example.ecommerce.product.model.entity.Product;
import com.example.ecommerce.util.JwtUtil;
import io.jsonwebtoken.Jwt;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CartService {
    private final CartRepository cartRepository;
    private final MemberRepository memberRepository;

    @Value("${jwt.secret-key}")
    private String key;

    // TODO: 장바구니 추가
    public void cartIn(GetCartInReq getCartInReq, String email) {
        Optional<Member> member = memberRepository.findByEmail(email);

        if (member.isPresent()) {
            cartRepository.save(Cart.dtoToEntity(getCartInReq, member.get()));
        }
    }

    // TODO: 장바구니에 담긴 목록
    public SuccessListRes cartList(String token) {
        if (token != null && token.startsWith("Bearer ")) {
            token = token.split(" ")[1];
        }

        Long id = JwtUtil.getUserId(token, key);
        List<Cart> carts = cartRepository.findAllByMember(Member.builder().id(id).build());
        List<GetCartListRes> cartList = new ArrayList<>();

        for (Cart cart : carts) {
            Product product = cart.getProduct();
            cartList.add(GetCartListRes.buildDto(cart, product));
        }

        return SuccessListRes.buildDto(cartList);
    }

    // TODO: 장바구니에서 해당 상품 제거
    public SuccessCancelRes cartCancel(Long id) {
        Optional<Cart> cart = cartRepository.findById(id);
        if (cart.isPresent()) {
            cartRepository.deleteById(id);

            return SuccessCancelRes.builder()
                    .isSuccess(true)
                    .code(1000)
                    .message("요청 성공")
                    .result(GetCartCancelRes.builder()
                            .idx(cart.get().getProduct().getId())
                            .status(false)
                            .build())
                    .success(true)
                    .build();
        } else {
            return null;
        }
    }
}
