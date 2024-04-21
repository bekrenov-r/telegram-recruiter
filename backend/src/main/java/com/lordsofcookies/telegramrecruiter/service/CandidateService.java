package com.lordsofcookies.telegramrecruiter.service;

import com.lordsofcookies.telegramrecruiter.dto.request.CandidateRequest;
import com.lordsofcookies.telegramrecruiter.dto.response.CandidateResponse;
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
        if(candidateRepository.existsByTelegramId(currentUser.getTelegramId())){
            return candidateMapper.entityToResponse(candidateRepository.findByTelegramIdOrThrowDefault(currentUser.getTelegramId()));
        }
        Candidate candidate = new Candidate(
                null,
                currentUser,
                request.enableOfferNotifications(),
                request.preferredLocationVoivodeship(),
                request.preferredTechnologies(),
                request.preferredLevels(),
                request.preferredWorkModes()
        );
        return candidateMapper.entityToResponse(candidateRepository.save(candidate));
    }

    public CandidateResponse updateCandidateProfile(CandidateRequest request) {
        TelegramUser currentUser = telegramUserRepository.findByTelegramIdOrThrowDefault(CurrentAuthUtil.getCurrentAuth().getName());
        Candidate candidate = candidateRepository.findByTelegramIdOrThrowDefault(currentUser.getTelegramId());
        candidate.setEnableOfferNotifications(request.enableOfferNotifications());
        candidate.setPreferredLocationVoivodeship(request.preferredLocationVoivodeship());
        candidate.setPreferredTechnologies(request.preferredTechnologies());
        candidate.setPreferredLevels(request.preferredLevels());
        candidate.setPreferredWorkModes(request.preferredWorkModes());
        return candidateMapper.entityToResponse(candidateRepository.save(candidate));
    }

    public void subscribeToNotifications(String telegramId) {
        TelegramUser telegramUser = telegramUserRepository.findByTelegramIdOrThrowDefault(telegramId);
        Candidate candidate = candidateRepository.findByTelegramIdOrThrowDefault(telegramUser.getTelegramId());
        candidate.setEnableOfferNotifications(true);
        candidateRepository.save(candidate);
    }

    public void unsubscribeFromNotifications(String telegramId) {
        TelegramUser telegramUser = telegramUserRepository.findByTelegramIdOrThrowDefault(telegramId);
        Candidate candidate = candidateRepository.findByTelegramIdOrThrowDefault(telegramUser.getTelegramId());
        candidate.setEnableOfferNotifications(true);
        candidateRepository.save(candidate);
    }
}
