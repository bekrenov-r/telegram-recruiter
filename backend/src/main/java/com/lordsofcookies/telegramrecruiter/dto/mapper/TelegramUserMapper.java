package com.lordsofcookies.telegramrecruiter.dto.mapper;

import com.lordsofcookies.telegramrecruiter.dto.response.TelegramUserResponse;
import com.lordsofcookies.telegramrecruiter.entity.TelegramUser;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class TelegramUserMapper {
    public abstract TelegramUserResponse entityToResponse(TelegramUser entity);
}
