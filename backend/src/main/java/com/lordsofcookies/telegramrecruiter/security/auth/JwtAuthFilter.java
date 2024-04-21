package com.lordsofcookies.telegramrecruiter.security.auth;

import com.lordsofcookies.telegramrecruiter.entity.TelegramUser;
import com.lordsofcookies.telegramrecruiter.repository.TelegramUserRepository;
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
        String token = getJwtFromHeader(request);
        System.out.println(token);
        if(jwtProvider.validateToken(token)){
            String userId = jwtProvider.getSubject(token);
            TelegramUser user = telegramUserRepository.findByTelegramIdOrThrowDefault(userId);
            if(user == null)
                throw new EntityNotFoundException("User with id " + userId + "not found");
            Authentication authToken = new JwtAuthenticationToken(null, userId, token);
            SecurityContextHolder.getContext().setAuthentication(authToken);
        } else throw new RuntimeException("JWT token is expired");
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

    private String getJwtFromHeader(HttpServletRequest request){
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(header == null)
            throw new RuntimeException("Authorization is required for this request");
        if(!header.startsWith(BEARER_PREFIX))
            throw new RuntimeException("Invalid auth header");
        return header.substring(BEARER_PREFIX.length());
    }
}
