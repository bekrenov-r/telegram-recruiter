package com.lordsofcookies.telegramrecruiter.repository;

import com.lordsofcookies.telegramrecruiter.entity.Recruiter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecruiterRepository extends JpaRepository<Recruiter, String> {
    Optional<Recruiter> findByTelegramId(String telegramId);

    default Recruiter findByTelegramIdOrThrowDefault(String telegramId){
        return findByTelegramId(telegramId).orElseThrow(RuntimeException::new);
    }
}
