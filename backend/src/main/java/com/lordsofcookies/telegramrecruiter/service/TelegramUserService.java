package com.lordsofcookies.telegramrecruiter.service;

import com.lordsofcookies.telegramrecruiter.entity.TelegramUser;
import com.lordsofcookies.telegramrecruiter.repository.TelegramUserRepository;
import com.lordsofcookies.telegramrecruiter.dto.TelegramUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TelegramUserService {
    private final TelegramUserRepository telegramUserRepository;

    public TelegramUser createUser(TelegramUserRequest request) {
        if(telegramUserRepository.existsByTelegramId(request.telegramId())){
            return updateUser(request);
        }
        TelegramUser tgUser = new TelegramUser();
        tgUser.setTelegramId(request.telegramId());
        tgUser.setFirstName(request.firstName());
        tgUser.setLastName(request.lastName());
        tgUser.setTelegramUsername(request.telegramUsername());
        return telegramUserRepository.save(tgUser);
    }

    public TelegramUser updateUser(TelegramUserRequest request){
        TelegramUser user = telegramUserRepository.findByTelegramIdOrThrowDefault(request.telegramId());
        user.setFirstName(request.firstName());
        user.setLastName(request.lastName());
        user.setTelegramUsername(request.telegramUsername());
        return telegramUserRepository.save(user);
    }
}
