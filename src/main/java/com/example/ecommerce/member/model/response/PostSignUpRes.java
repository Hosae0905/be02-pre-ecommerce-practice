package com.example.ecommerce.member.model.response;

import com.example.ecommerce.member.model.entity.Member;
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

    public static PostSignUpRes buildDto(Member member, String accessToken) {
        return PostSignUpRes.builder()
                .id(member.getId())
                .email(member.getEmail())
                .accessToken(accessToken)
                .build();
    }
}
