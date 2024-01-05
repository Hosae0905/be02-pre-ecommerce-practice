package com.example.ecommerce.member.model.response;

import com.example.ecommerce.member.model.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Builder
@Setter
@Getter
public class PostSignUpRes {
    @NotNull
    private Long id;
    @NotNull
    @Pattern(regexp = "^[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*\\.[a-zA-Z]{2,3}+$")
    private String email;
    @NotNull
    private String accessToken;

    public static PostSignUpRes buildDto(Member member, String accessToken) {
        return PostSignUpRes.builder()
                .id(member.getId())
                .email(member.getEmail())
                .accessToken(accessToken)
                .build();
    }
}
