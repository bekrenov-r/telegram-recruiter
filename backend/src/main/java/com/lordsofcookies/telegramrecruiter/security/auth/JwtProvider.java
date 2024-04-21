package com.lordsofcookies.telegramrecruiter.security.auth;

import com.lordsofcookies.telegramrecruiter.entity.TelegramUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.Map;

@Component
public class JwtProvider implements InitializingBean {
    private Key key;

    @Value("${spring.security.jwt.secret}")
    private String secretKey;

    @Value("${spring.security.jwt.expiration-time-millis}")
    private long expirationTimeMillis;

    @Override
    public void afterPropertiesSet() throws Exception {
        key = Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    public String generateToken(TelegramUser telegramUser){
        Map<String, String> claims = Map.of(
                "firstName", telegramUser.getFirstName(),
                "lastName", telegramUser.getLastName()
        );
        Date currentDate = new Date(System.currentTimeMillis());
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(telegramUser.getUsername())
                .setIssuedAt(currentDate)
                .setExpiration(new Date(System.currentTimeMillis() + expirationTimeMillis))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validateToken(String token){
        Date expiration = getAllClaims(token).getExpiration();
        Date currentDate = new Date();
        return expiration.after(currentDate);
    }

    public Claims getAllClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String getSubject(String token){
        return getAllClaims(token).getSubject();
    }
}
