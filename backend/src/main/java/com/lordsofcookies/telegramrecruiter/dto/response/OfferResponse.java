package com.lordsofcookies.telegramrecruiter.dto.response;

import com.lordsofcookies.telegramrecruiter.enums.Level;
import com.lordsofcookies.telegramrecruiter.enums.Position;
import com.lordsofcookies.telegramrecruiter.enums.Technology;
import com.lordsofcookies.telegramrecruiter.enums.WorkMode;

import java.util.Set;
import java.util.UUID;

public record OfferResponse(
        UUID id,
        String name,
        String description,
        String city,
        Position position,
        Level level,
        WorkMode workMode,
        Set<Technology> technologies,
        CompanyResponse companyResponse
) { }
