package com.lordsofcookies.telegramrecruiter.dto.request;

import com.lordsofcookies.telegramrecruiter.enums.Level;
import com.lordsofcookies.telegramrecruiter.enums.Position;
import com.lordsofcookies.telegramrecruiter.enums.Technology;
import com.lordsofcookies.telegramrecruiter.enums.WorkMode;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public record OffersSearchCriteria(
        List<Position> positions,
        List<Technology> technologies,
        List<Level> levels,
        List<WorkMode> workModes,
        String searchPattern,
        String city
) { }
