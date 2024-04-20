package com.lordsofcookies.telegramrecruiter.controller;

import com.lordsofcookies.telegramrecruiter.dto.request.RecruiterRequest;
import com.lordsofcookies.telegramrecruiter.dto.response.RecruiterResponse;
import com.lordsofcookies.telegramrecruiter.service.RecruiterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recruiters")
@RequiredArgsConstructor
public class RecruiterController {
    private final RecruiterService recruiterService;

    @PostMapping
    public ResponseEntity<RecruiterResponse> createRecruiterProfile(@RequestBody @Valid RecruiterRequest request){
        return ResponseEntity.ok(recruiterService.createRecruiterProfile(request));
    }
}
