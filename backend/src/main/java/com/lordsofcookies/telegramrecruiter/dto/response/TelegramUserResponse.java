package com.lordsofcookies.telegramrecruiter.dto.response;

import com.lordsofcookies.telegramrecruiter.enums.Level;
import com.lordsofcookies.telegramrecruiter.enums.Position;
import com.lordsofcookies.telegramrecruiter.enums.Technology;
import com.lordsofcookies.telegramrecruiter.enums.WorkMode;

import java.util.Set;

public record TelegramUserResponse(
        String telegramId,
        String firstName,
        String lastName,
        String email,
        String telegramUsername
) {}
