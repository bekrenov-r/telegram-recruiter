package com.lordsofcookies.telegramrecruiter.repository;

import com.lordsofcookies.telegramrecruiter.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CompanyRepository extends JpaRepository<Company, UUID> {
    default Company findByIdOrThrowDefault(UUID id){
        return findById(id).orElseThrow(RuntimeException::new);
    }
}
