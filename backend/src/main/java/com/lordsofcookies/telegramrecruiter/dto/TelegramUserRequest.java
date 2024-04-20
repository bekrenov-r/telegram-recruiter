package com.lordsofcookies.telegramrecruiter.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public record TelegramUserRequest(
        @NotNull
        String telegramId,
        @JsonProperty("first_name")
        String firstName,
        @JsonProperty("last_name")
        String lastName,
        @JsonProperty("username")
        String telegramUsername
) { }
