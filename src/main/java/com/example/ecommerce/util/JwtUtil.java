package com.example.ecommerce.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtUtil {
    public static String generateAccessToken(String username, String key, int expiredTimeMs) {
        Claims claims = Jwts.claims();
        claims.put("username", username);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiredTimeMs))
                .signWith(getSignKey(key), SignatureAlgorithm.HS256)
                .compact();
    }

    public static Claims extractAllClaim(String token, String key) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey(key))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public static Boolean validate(String token, String username, String key) {
        String usernameByToken = getUsername(token, key);
        Date expiration = extractAllClaim(token, key).getExpiration();

        Boolean before = expiration.before(new Date(System.currentTimeMillis()));

        return usernameByToken.equals(username) && !before;
    }

    public static String getUsername(String token, String key) {
        return extractAllClaim(token, key).get("username", String.class);
    }

    public static Key getSignKey(String key) {
        return Keys.hmacShaKeyFor(key.getBytes());
    }
}
