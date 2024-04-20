package com.lordsofcookies.telegramrecruiter.controller;

import com.lordsofcookies.telegramrecruiter.service.RecruiterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recruiters")
@RequiredArgsConstructor
public class RecruiterController {
    private final RecruiterService recruiterService;
}
