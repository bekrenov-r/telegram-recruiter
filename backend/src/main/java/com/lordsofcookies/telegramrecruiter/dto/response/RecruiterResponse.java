package com.lordsofcookies.telegramrecruiter.dto.response;

public record RecruiterResponse(
        TelegramUserResponse telegramUser,
        CompanyResponse company
) { }
