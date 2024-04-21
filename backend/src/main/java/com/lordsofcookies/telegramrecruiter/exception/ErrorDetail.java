package com.lordsofcookies.telegramrecruiter.exception;

import org.springframework.http.HttpStatus;

public record ErrorDetail(
        HttpStatus status,
        String message
) { }
