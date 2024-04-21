package com.lordsofcookies.telegramrecruiter.security.auth;

import com.lordsofcookies.telegramrecruiter.entity.TelegramUser;
import com.lordsofcookies.telegramrecruiter.repository.TelegramUserRepository;
import io.jsonwebtoken.JwtException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    @Value("${spring.security.request-matchers.permit-all}")
    private String[] permitAllMatchers;

    private final TelegramUserRepository telegramUserRepository;
    private final JwtProvider jwtProvider;

    private static final String BEARER_PREFIX = "Bearer ";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(request.getHeader(HttpHeaders.AUTHORIZATION) == null){
            filterChain.doFilter(request, response);
        }
        String userId = request.getHeader(HttpHeaders.AUTHORIZATION);
        var authentication = new JwtAuthenticationToken(null, userId);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request){
        return Arrays.stream(permitAllMatchers)
                .map(s -> {
                    String[] arr = s.split(" ");
                    return new AntPathRequestMatcher(arr[1], arr[0]);
                }).anyMatch(m -> m.matches(request));
    }
}
