package com.lordsofcookies.telegramrecruiter.repository;

import com.lordsofcookies.telegramrecruiter.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface CandidateRepository extends JpaRepository<Candidate, String>, JpaSpecificationExecutor<Candidate> {
    Optional<Candidate> findByTelegramId(String telegramId);
    default Candidate findByTelegramIdOrThrowDefault(String telegramId){
        return findByTelegramId(telegramId).orElseThrow(RuntimeException::new);
    }
}
