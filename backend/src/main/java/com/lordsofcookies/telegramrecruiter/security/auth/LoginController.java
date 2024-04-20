package com.lordsofcookies.telegramrecruiter.security.auth;

import com.lordsofcookies.telegramrecruiter.dto.TelegramUserRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;
    @PostMapping("/telegram")
    public ResponseEntity<String> createUser(@RequestBody @Valid TelegramUserRequest request){
        return ResponseEntity.ok(loginService.loginWithTelegram(request));
    }
}
