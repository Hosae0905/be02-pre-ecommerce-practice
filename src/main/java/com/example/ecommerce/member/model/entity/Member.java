package com.example.ecommerce.member.model.entity;

import com.example.ecommerce.cart.model.entity.Cart;
import com.example.ecommerce.member.model.request.PostSellerSignUpReq;
import com.example.ecommerce.member.model.request.PostSignUpReq;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Member implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String nickname;
    private String password;
    private String role;
    private Boolean isValid;

    @OneToMany(mappedBy = "member")
    private List<Cart> cartList;

    public static Member dtoToEntity(PostSignUpReq postSignUpReq) {
        return Member.builder()
                .email(postSignUpReq.getEmail())
                .nickname(postSignUpReq.getNickname())
                .password(postSignUpReq.getPassword())
                .role("USER")
                .isValid(false)
                .build();
    }

    public static Member dtoToEntity(PostSellerSignUpReq postSellerSignUpReq) {
        return Member.builder()
                .email(postSellerSignUpReq.getEmail())
                .nickname(postSellerSignUpReq.getNickName())
                .password(postSellerSignUpReq.getPassword())
                .role("SELLER")
                .isValid(false)
                .build();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
