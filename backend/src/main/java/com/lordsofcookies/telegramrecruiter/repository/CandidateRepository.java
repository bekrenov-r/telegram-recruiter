package com.lordsofcookies.telegramrecruiter.repository;

import com.lordsofcookies.telegramrecruiter.entity.Candidate;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface CandidateRepository extends JpaRepository<Candidate, String>, JpaSpecificationExecutor<Candidate> {
    Optional<Candidate> findByTelegramId(String telegramId);
    boolean existsByTelegramId(String telegramId);
    default Candidate findByTelegramIdOrThrowDefault(String telegramId){
        return findByTelegramId(telegramId)
                .orElseThrow(() -> new EntityNotFoundException("Candidate with id [" + telegramId + "] not found"));
    }
}
