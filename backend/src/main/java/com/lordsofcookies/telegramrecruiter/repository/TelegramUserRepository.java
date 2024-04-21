package com.lordsofcookies.telegramrecruiter.repository;

import com.lordsofcookies.telegramrecruiter.entity.TelegramUser;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TelegramUserRepository extends JpaRepository<TelegramUser, Long> {
    Optional<TelegramUser> findByTelegramId(String telegramId);
    boolean existsByTelegramId(String telegramId);

    default TelegramUser findByTelegramIdOrThrowDefault(String telegramId){
        return findByTelegramId(telegramId)
                .orElseThrow(() -> new EntityNotFoundException("Telegram user with id [" + telegramId + "] not found"));
    }
}
