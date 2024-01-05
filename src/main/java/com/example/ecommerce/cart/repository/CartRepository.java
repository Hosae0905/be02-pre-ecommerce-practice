package com.example.ecommerce.cart.repository;

import com.example.ecommerce.cart.model.entity.Cart;
import com.example.ecommerce.member.model.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findAllByMember(Member member);
}
