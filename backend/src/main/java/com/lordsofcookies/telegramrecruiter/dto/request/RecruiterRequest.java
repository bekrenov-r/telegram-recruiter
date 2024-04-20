package com.lordsofcookies.telegramrecruiter.dto.request;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record RecruiterRequest(
        @NotNull
        UUID companyId
) { }
