package com.lordsofcookies.telegramrecruiter.service;

import com.lordsofcookies.telegramrecruiter.dto.CandidateRequest;
import com.lordsofcookies.telegramrecruiter.dto.CandidateResponse;
import com.lordsofcookies.telegramrecruiter.dto.mapper.CandidateMapper;
import com.lordsofcookies.telegramrecruiter.entity.Candidate;
import com.lordsofcookies.telegramrecruiter.entity.TelegramUser;
import com.lordsofcookies.telegramrecruiter.repository.CandidateRepository;
import com.lordsofcookies.telegramrecruiter.repository.TelegramUserRepository;
import com.lordsofcookies.telegramrecruiter.util.CurrentAuthUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CandidateService {
    private final CandidateRepository candidateRepository;
    private final TelegramUserRepository telegramUserRepository;
    private final CandidateMapper candidateMapper;

    public CandidateResponse createCandidateProfile(CandidateRequest request) {
        TelegramUser currentUser = telegramUserRepository.findByTelegramIdOrThrowDefault(CurrentAuthUtil.getCurrentAuth().getName());
        Candidate candidate = new Candidate(
                null,
                currentUser,
                request.email(),
                request.preferredLocationVoivodeship(),
                request.preferredTechnologies(),
                request.preferredPositions(),
                request.preferredLevels(),
                request.preferredWorkModes()
        );
        var save = candidateRepository.save(candidate);
        return candidateMapper.entityToResponse(save);
    }
}
