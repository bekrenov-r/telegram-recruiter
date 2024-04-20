package com.lordsofcookies.telegramrecruiter.service;

import com.lordsofcookies.telegramrecruiter.dto.mapper.OfferMapper;
import com.lordsofcookies.telegramrecruiter.dto.request.OffersSearchCriteria;
import com.lordsofcookies.telegramrecruiter.dto.response.OfferResponse;
import com.lordsofcookies.telegramrecruiter.entity.Offer;
import com.lordsofcookies.telegramrecruiter.repository.OfferRepository;
import com.lordsofcookies.telegramrecruiter.repository.OfferSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OfferService {
    private final OfferRepository offerRepository;
    private final OfferMapper offerMapper;
    public List<OfferResponse> getOffersByCriteria(OffersSearchCriteria criteria) {
        Specification<Offer> specification = OfferSpecification.fromSearchCriteria(criteria);
        List<Offer> offers = offerRepository.findAll(specification, Sort.by("createdAt"));
        return offers.stream()
                .map(offerMapper::entityToResponse)
                .toList();
    }
}
