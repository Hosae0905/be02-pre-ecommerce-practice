package com.example.ecommerce.member.service;

import com.example.ecommerce.member.model.request.PostAuthenticateReq;
import com.example.ecommerce.member.model.request.PostEmailConfirmReq;
import com.example.ecommerce.member.model.request.PostSellerSignUpReq;
import com.example.ecommerce.member.model.request.PostSignUpReq;
import com.example.ecommerce.member.model.entity.Member;
import com.example.ecommerce.member.model.response.PostSignUpRes;
import com.example.ecommerce.member.model.response.SignUpSuccessRes;
import com.example.ecommerce.member.repository.MemberRepository;
import com.example.ecommerce.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.RedirectView;

import java.util.*;

@Service
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JavaMailSender emailSender;

    @Value("${jwt.secret-key}")
    private String key;

    @Value("${jwt.expiredTimeMs}")
    private Integer expiredTimeMs;

    public Map<String, String> authenticate(PostAuthenticateReq postAuthenticateReq) {
        Optional<Member> member = memberRepository.findByEmail(postAuthenticateReq.getUsername());
        if (member.isPresent()) {
            if(passwordEncoder.matches(postAuthenticateReq.getPassword(), member.get().getPassword())) {

                Map<String, String> response = new HashMap<>();
                response.put("token", JwtUtil.generateAccessToken(postAuthenticateReq.getUsername(), key, expiredTimeMs));
                return response;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public Boolean checkEmail(String email) {
        Optional<Member> member = memberRepository.findByEmail(email);
        if (member.isPresent()) {
            return false;
        } else {
            return true;
        }

    }

    public PostSignUpRes signUp(PostSignUpReq postSignUpReq) {
        postSignUpReq.setPassword(passwordEncoder.encode(postSignUpReq.getPassword()));
        Member member = memberRepository.save(Member.dtoToEntity(postSignUpReq));
        String accessToken = JwtUtil.generateAccessToken(member.getUsername(), key, expiredTimeMs);

        return PostSignUpRes.builder()
                .id(member.getId())
                .email(member.getEmail())
                .accessToken(accessToken)
                .build();
    }

    public SignUpSuccessRes successRes(PostSignUpRes postSignUpRes) {

        Map<String, Object> result = new HashMap<>();
        result.put("idx", postSignUpRes.getId());
        result.put("status", 0);

        return SignUpSuccessRes.builder()
                .isSuccess(true)
                .code(1000)
                .message("요청 성공")
                .result(result)
                .success(true)
                .build();
    }

    public void sellerSignUp(PostSellerSignUpReq postSellerSignUpReq) {
        memberRepository.save(Member.dtoToEntity(postSellerSignUpReq));
    }

    public void sendEmail(PostSignUpRes postSignUpRes) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        String uuid = UUID.randomUUID().toString();

        mailMessage.setTo(postSignUpRes.getEmail());
        mailMessage.setSubject("이메일 인증 절차를 완료해주세요.");
        mailMessage.setText("http://localhost:8080/member/confirm?email="
                + postSignUpRes.getEmail()
                + "&uuid=" + uuid
                + "&jwt=" + postSignUpRes.getAccessToken()
        );
        emailSender.send(mailMessage);
    }

    public RedirectView confirm(String email, String uuid, String jwt) {
        if (email != null) {
            updateValid(email);
            return new RedirectView("http://localhost:3000/emailconfirm/" + jwt);
        } else {
            return new RedirectView("http://localhost:3000/emailCertError");
        }
    }

    public void updateValid(String email) {
        Optional<Member> member = memberRepository.findByEmail(email);
        if (member.isPresent()) {
            member.get().setIsValid(true);
            memberRepository.save(member.get());
        }
    }

    @Override
    public Member loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Member> member = memberRepository.findByEmail(email);

        if (member.isPresent()) {
            return member.get();
        } else {
            return null;
        }

    }
}
