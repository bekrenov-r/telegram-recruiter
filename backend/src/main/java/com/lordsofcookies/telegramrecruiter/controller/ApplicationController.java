package com.lordsofcookies.telegramrecruiter.controller;

import com.lordsofcookies.telegramrecruiter.dto.request.ApplicationRequest;
import com.lordsofcookies.telegramrecruiter.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/applications")
@RequiredArgsConstructor
public class ApplicationController {
    private final ApplicationService applicationService;

    @PostMapping
    public ResponseEntity<Void> createApplication(@RequestBody ApplicationRequest request){
        applicationService.createApplication(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @PatchMapping("/{id}/cv")
    public void attachCVToApplication(@PathVariable UUID id, @RequestParam("file") MultipartFile file) throws IOException {
        applicationService.attachCVToApplication(id, file);
    }
}
