package com.lordsofcookies.telegramrecruiter.dto.request;

import java.util.UUID;

public record ApplicationRequest(
        UUID offerId,
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        String messageToRecruiter
) { }
