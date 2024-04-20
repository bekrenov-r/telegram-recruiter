package com.lordsofcookies.telegramrecruiter.controller;

import com.lordsofcookies.telegramrecruiter.dto.request.CandidateRequest;
import com.lordsofcookies.telegramrecruiter.dto.response.CandidateResponse;
import com.lordsofcookies.telegramrecruiter.service.CandidateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/candidates")
@RequiredArgsConstructor
public class CandidateController {
    private final CandidateService candidateService;

    @PostMapping
    public ResponseEntity<CandidateResponse> createCandidateProfile(@RequestBody @Valid CandidateRequest request){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(candidateService.createCandidateProfile(request));
    }
}
