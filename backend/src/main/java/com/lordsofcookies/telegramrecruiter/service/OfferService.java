package com.lordsofcookies.telegramrecruiter.service;

import com.lordsofcookies.telegramrecruiter.dto.mapper.OfferMapper;
import com.lordsofcookies.telegramrecruiter.dto.request.OfferRequest;
import com.lordsofcookies.telegramrecruiter.dto.request.OffersSearchCriteria;
import com.lordsofcookies.telegramrecruiter.dto.response.OfferResponse;
import com.lordsofcookies.telegramrecruiter.entity.Offer;
import com.lordsofcookies.telegramrecruiter.entity.Recruiter;
import com.lordsofcookies.telegramrecruiter.entity.TelegramUser;
import com.lordsofcookies.telegramrecruiter.repository.OfferRepository;
import com.lordsofcookies.telegramrecruiter.repository.OfferSpecification;
import com.lordsofcookies.telegramrecruiter.repository.RecruiterRepository;
import com.lordsofcookies.telegramrecruiter.repository.TelegramUserRepository;
import com.lordsofcookies.telegramrecruiter.util.CurrentAuthUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OfferService {
    private final OfferRepository offerRepository;
    private final OfferMapper offerMapper;
    private final OfferNotifier offerNotifier;
    private final RecruiterRepository recruiterRepository;
    private final TelegramUserRepository telegramUserRepository;

    public List<OfferResponse> getOffersByCriteria(OffersSearchCriteria criteria) {
        Specification<Offer> specification = OfferSpecification.fromSearchCriteria(criteria);
        List<Offer> offers = offerRepository.findAll(specification, Sort.by("createdAt"));
        return offers.stream()
                .map(offerMapper::entityToResponse)
                .toList();
    }

    @Transactional
    public OfferResponse createOffer(OfferRequest request) {
        Offer offer = offerMapper.requestToEntity(request);
        TelegramUser currentUser = telegramUserRepository.findByTelegramIdOrThrowDefault(CurrentAuthUtil.getCurrentAuth().getName());
        Recruiter recruiter = recruiterRepository.findByTelegramIdOrThrowDefault(currentUser.getTelegramId());
        offer.setCreatedByRecruiter(recruiter);
        offer.setCreatedAt(LocalDateTime.now());
        Offer savedOffer = offerRepository.save(offer);
        offerNotifier.notifyCandidates(savedOffer);
        return offerMapper.entityToResponse(savedOffer);
    }
}
