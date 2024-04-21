package com.lordsofcookies.telegramrecruiter.dto.request;

import com.lordsofcookies.telegramrecruiter.enums.Level;
import com.lordsofcookies.telegramrecruiter.enums.Position;
import com.lordsofcookies.telegramrecruiter.enums.Technology;
import com.lordsofcookies.telegramrecruiter.enums.WorkMode;

import java.util.Set;
import java.util.UUID;

public record OfferRequest(
        String name,
        String description,
        String city,
        Position position,
        Level level,
        WorkMode workMode,
        Set<Technology> technologies,
        UUID companyId
) { }
