package com.lordsofcookies.telegramrecruiter.security.auth;

import com.lordsofcookies.telegramrecruiter.repository.TelegramUserRepository;
import com.lordsofcookies.telegramrecruiter.entity.TelegramUser;
import com.lordsofcookies.telegramrecruiter.dto.request.TelegramUserRequest;
import com.lordsofcookies.telegramrecruiter.service.TelegramUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final TelegramUserRepository telegramUserRepository;
    private final TelegramUserService telegramUserService;
    private final JwtProvider jwtProvider;

    public String loginWithTelegram(TelegramUserRequest request) {
        if(telegramUserRepository.existsByTelegramId(request.telegramId())){
            TelegramUser updatedUser = telegramUserService.updateUser(request);
            return jwtProvider.generateToken(updatedUser);
        }

        TelegramUser savedUser = telegramUserService.createUser(request);
        String s = jwtProvider.generateToken(savedUser);
        System.out.println(s);
        return s;
    }


}
