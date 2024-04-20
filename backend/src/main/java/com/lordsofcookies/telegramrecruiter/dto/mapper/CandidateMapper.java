package com.lordsofcookies.telegramrecruiter.dto.mapper;

import com.lordsofcookies.telegramrecruiter.dto.CandidateResponse;
import com.lordsofcookies.telegramrecruiter.entity.Candidate;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = TelegramUserMapper.class)
public abstract class CandidateMapper {
    public abstract CandidateResponse entityToResponse(Candidate entity);
}
