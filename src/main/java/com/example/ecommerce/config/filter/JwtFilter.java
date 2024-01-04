package com.example.ecommerce.config.filter;

import com.example.ecommerce.member.model.entity.Member;
import com.example.ecommerce.member.service.MemberService;
import com.example.ecommerce.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final MemberService memberService;

    private final String key;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        String token;

        if (header != null && header.startsWith("Bearer ")) {
            token = header.split(" ")[1];
        } else {
            filterChain.doFilter(request, response);
            return;
        }

        String username = JwtUtil.getUsername(token, key);
        Member member = memberService.loadUserByUsername(username);

        if (!JwtUtil.validate(token, member.getUsername(), key)) {
            filterChain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(member.getUsername(), member.getPassword(), member.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }
}
