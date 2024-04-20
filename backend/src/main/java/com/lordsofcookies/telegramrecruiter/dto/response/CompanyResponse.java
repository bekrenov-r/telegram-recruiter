package com.lordsofcookies.telegramrecruiter.dto.response;

import java.util.UUID;

public record CompanyResponse(
        UUID id,
        String name,
        TelegramUserResponse createdBy
) { }
