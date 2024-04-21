package com.lordsofcookies.telegramrecruiter.repository;

import com.lordsofcookies.telegramrecruiter.entity.Application;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ApplicationRepository extends JpaRepository<Application, UUID> {
    default Application findByIdOrThrowDefault(UUID id){
        return findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Application with id [" + id + "] not found"));
    }
}
