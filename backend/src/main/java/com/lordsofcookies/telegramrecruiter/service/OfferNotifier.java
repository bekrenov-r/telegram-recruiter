package com.lordsofcookies.telegramrecruiter.service;

import com.lordsofcookies.telegramrecruiter.dto.request.OfferNotification;
import com.lordsofcookies.telegramrecruiter.entity.Candidate;
import com.lordsofcookies.telegramrecruiter.entity.Offer;
import com.lordsofcookies.telegramrecruiter.repository.CandidateRepository;
import com.lordsofcookies.telegramrecruiter.repository.CandidateSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OfferNotifier {
    private final CandidateRepository candidateRepository;

    public void notifyCandidates(Offer offer){
        List<Candidate> candidates =  candidateRepository.findAll(CandidateSpecification.candidateFitsToOffer(offer));
        if(candidates.isEmpty()) return;
        List<String> candidatesIds = candidates.stream()
                .map(Candidate::getTelegramId)
                .toList();
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<OfferNotification> httpEntity = new HttpEntity<>(new OfferNotification(offer.getId(), offer.getName(), candidatesIds));
        restTemplate.postForLocation("http://localhost:3000/send-offers", httpEntity);
    }
}
