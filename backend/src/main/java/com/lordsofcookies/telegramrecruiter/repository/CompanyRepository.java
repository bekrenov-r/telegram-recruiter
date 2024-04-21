package com.lordsofcookies.telegramrecruiter.repository;

import com.lordsofcookies.telegramrecruiter.entity.Company;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CompanyRepository extends JpaRepository<Company, UUID> {
    default Company findByIdOrThrowDefault(UUID id){
        return findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Company with id [" + id + "] not found"));
    }
}
