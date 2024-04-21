package com.lordsofcookies.telegramrecruiter.controller;

import com.lordsofcookies.telegramrecruiter.dto.response.TelegramUserResponse;
import com.lordsofcookies.telegramrecruiter.service.TelegramUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class TelegramUserController {
    private final TelegramUserService telegramUserService;

    @GetMapping("/{telegramId}")
    public ResponseEntity<TelegramUserResponse> getUserById(@PathVariable String telegramId){
        return ResponseEntity.ok(telegramUserService.getUserById(telegramId));
    }
}
