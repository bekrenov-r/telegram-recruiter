package com.lordsofcookies.telegramrecruiter.repository;

import com.lordsofcookies.telegramrecruiter.entity.Offer;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface OfferRepository extends JpaRepository<Offer, UUID>, JpaSpecificationExecutor<Offer> {
    default Offer findByIdOrThrowDefault(UUID id){
        return findById(id).orElseThrow(() -> new EntityNotFoundException("Offer with id [" + id + "] not found"));
    }
}
