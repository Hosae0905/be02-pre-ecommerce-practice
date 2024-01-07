package com.example.ecommerce.member.service;

import com.example.ecommerce.exception.ApplicationException;
import com.example.ecommerce.exception.ErrorCode;
import com.example.ecommerce.member.model.request.PostAuthenticateReq;
import com.example.ecommerce.member.model.request.PostSellerSignUpReq;
import com.example.ecommerce.member.model.request.PostSignUpReq;
import com.example.ecommerce.member.model.entity.Member;
import com.example.ecommerce.member.model.response.PostSignUpRes;
import com.example.ecommerce.member.model.response.SuccessSignUpRes;
import com.example.ecommerce.member.repository.MemberRepository;
import com.example.ecommerce.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
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
                response.put("token", JwtUtil.generateAccessToken(member.get(), key, expiredTimeMs));
                return response;
            } else {
                throw new ApplicationException(ErrorCode.INVALID_PASSWORD);
            }
        } else {
            return null;
        }
    }

    public Boolean checkEmail(String email) {
        Optional<Member> member = memberRepository.findByEmail(email);
        if (member.isPresent()) {
            throw new ApplicationException(ErrorCode.DUPLICATE_USER);
        } else {
            return true;
        }

    }

    public PostSignUpRes signUp(PostSignUpReq postSignUpReq) {
        postSignUpReq.setPassword(passwordEncoder.encode(postSignUpReq.getPassword()));
        Member member = memberRepository.save(Member.dtoToEntity(postSignUpReq));
        String accessToken = JwtUtil.generateAccessToken(member, key, expiredTimeMs);
        PostSignUpRes postSignUpRes = PostSignUpRes.buildDto(member, accessToken);
        sendEmail(postSignUpRes);

        return postSignUpRes;
    }

    public SuccessSignUpRes successRes(PostSignUpRes postSignUpRes) {
        Map<String, Object> result = new HashMap<>();
        result.put("idx", postSignUpRes.getId());
        result.put("status", 0);
        return SuccessSignUpRes.buildDto(result);
    }

    public void sellerSignUp(PostSellerSignUpReq postSellerSignUpReq) {
        postSellerSignUpReq.setPassword(passwordEncoder.encode(postSellerSignUpReq.getPassword()));
        Member member = memberRepository.save(Member.dtoToEntity(postSellerSignUpReq));
        String accessToken = JwtUtil.generateAccessToken(member, key, expiredTimeMs);
        PostSignUpRes postSignUpRes = PostSignUpRes.buildDto(member, accessToken);
        sendEmail(postSignUpRes);
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

    private void updateValid(String email) {
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
