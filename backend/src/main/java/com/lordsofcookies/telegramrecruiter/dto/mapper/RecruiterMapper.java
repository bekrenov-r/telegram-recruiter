package com.lordsofcookies.telegramrecruiter.dto.mapper;

import com.lordsofcookies.telegramrecruiter.dto.response.RecruiterResponse;
import com.lordsofcookies.telegramrecruiter.entity.Recruiter;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {TelegramUserMapper.class, CompanyMapper.class})
public abstract class RecruiterMapper {
    public abstract RecruiterResponse entityToResponse(Recruiter entity);
}
