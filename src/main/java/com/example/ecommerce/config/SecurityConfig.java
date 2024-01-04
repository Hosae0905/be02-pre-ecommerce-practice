package com.example.ecommerce.config;

import com.example.ecommerce.config.filter.JwtFilter;
import com.example.ecommerce.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    @Value("${jwt.secret-key}")
    private String key;

    private final MemberService memberService;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{
        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {
        try {
            http.csrf().disable()
                    .authorizeRequests()
                    .antMatchers("/member/*").permitAll()
                    .antMatchers("/product/*").permitAll()
                    .antMatchers("/product/create").hasRole("SELLER")
                    .antMatchers("/product/display").hasRole("SELLER")
                    .anyRequest().authenticated();
            http.formLogin().disable();
            http.addFilterBefore(new JwtFilter(memberService, key), UsernamePasswordAuthenticationFilter.class);
            http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
            return http.build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
