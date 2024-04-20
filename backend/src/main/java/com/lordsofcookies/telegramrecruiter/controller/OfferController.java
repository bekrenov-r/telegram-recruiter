package com.lordsofcookies.telegramrecruiter.controller;

import com.lordsofcookies.telegramrecruiter.dto.request.OffersSearchCriteria;
import com.lordsofcookies.telegramrecruiter.dto.response.OfferResponse;
import com.lordsofcookies.telegramrecruiter.enums.Level;
import com.lordsofcookies.telegramrecruiter.enums.Position;
import com.lordsofcookies.telegramrecruiter.enums.Technology;
import com.lordsofcookies.telegramrecruiter.enums.WorkMode;
import com.lordsofcookies.telegramrecruiter.service.OfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/offers")
@RequiredArgsConstructor
public class OfferController {
    private final OfferService offerService;

    @GetMapping
    public ResponseEntity<List<OfferResponse>> getOffersByCriteria(
            @ModelAttribute OffersSearchCriteria criteria
    ){
        return ResponseEntity.ok(offerService.getOffersByCriteria(criteria));
    }
}
