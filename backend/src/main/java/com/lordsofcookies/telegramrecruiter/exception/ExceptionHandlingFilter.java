package com.lordsofcookies.telegramrecruiter.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class ExceptionHandlingFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch(JwtException ex) {
            handleJwtException(ex, response);
        }
    }

    private void handleJwtException(JwtException ex, HttpServletResponse response) throws IOException {
        ErrorDetail error = new ErrorDetail(HttpStatus.FORBIDDEN, ex.getMessage());
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.getWriter().write(convertErrorDetailToString(error));
    }

    private String convertErrorDetailToString(ErrorDetail error) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(error);
    }

}
