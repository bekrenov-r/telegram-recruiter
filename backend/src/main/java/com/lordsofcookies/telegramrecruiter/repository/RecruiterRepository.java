package com.lordsofcookies.telegramrecruiter.repository;

import com.lordsofcookies.telegramrecruiter.entity.Recruiter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecruiterRepository extends JpaRepository<Recruiter, String> {
}
