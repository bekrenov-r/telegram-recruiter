package com.lordsofcookies.telegramrecruiter.dto;

import com.lordsofcookies.telegramrecruiter.enums.Level;
import com.lordsofcookies.telegramrecruiter.enums.Position;
import com.lordsofcookies.telegramrecruiter.enums.Technology;
import com.lordsofcookies.telegramrecruiter.enums.WorkMode;

import java.util.Set;

public record CandidateResponse(
        TelegramUserResponse telegramUser,
        String preferredLocationVoivodeship,
        Set<Technology> preferredTechnologies,
        Set<Position> preferredPositions,
        Set<Level> preferredLevels,
        Set<WorkMode> preferredWorkModes
) { }


