package com.lordsofcookies.telegramrecruiter.dto.request;

import com.lordsofcookies.telegramrecruiter.enums.Level;
import com.lordsofcookies.telegramrecruiter.enums.Position;
import com.lordsofcookies.telegramrecruiter.enums.Technology;
import com.lordsofcookies.telegramrecruiter.enums.WorkMode;

import java.util.Set;

public record CandidateRequest(
        boolean enableOfferNotifications,
        String preferredLocationVoivodeship,
        Set<Technology> preferredTechnologies,
        Set<Level> preferredLevels,
        Set<WorkMode> preferredWorkModes
) {}
