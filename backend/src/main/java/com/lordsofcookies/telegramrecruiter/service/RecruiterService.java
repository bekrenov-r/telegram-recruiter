package com.lordsofcookies.telegramrecruiter.service;

import com.lordsofcookies.telegramrecruiter.dto.mapper.RecruiterMapper;
import com.lordsofcookies.telegramrecruiter.dto.request.RecruiterRequest;
import com.lordsofcookies.telegramrecruiter.dto.response.RecruiterResponse;
import com.lordsofcookies.telegramrecruiter.entity.Company;
import com.lordsofcookies.telegramrecruiter.entity.Recruiter;
import com.lordsofcookies.telegramrecruiter.entity.TelegramUser;
import com.lordsofcookies.telegramrecruiter.repository.CompanyRepository;
import com.lordsofcookies.telegramrecruiter.repository.RecruiterRepository;
import com.lordsofcookies.telegramrecruiter.repository.TelegramUserRepository;
import com.lordsofcookies.telegramrecruiter.security.Role;
import com.lordsofcookies.telegramrecruiter.util.CurrentAuthUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecruiterService {
    private final TelegramUserRepository telegramUserRepository;
    private final CompanyRepository companyRepository;
    private final RecruiterMapper recruiterMapper;
    private final RecruiterRepository recruiterRepository;

    public RecruiterResponse createRecruiterProfile(RecruiterRequest request) {
        TelegramUser telegramUser = telegramUserRepository.findByTelegramIdOrThrowDefault(CurrentAuthUtil.getCurrentAuth().getName());
        telegramUser.setRole(Role.RECRUITER);
        Company company = companyRepository.findByIdOrThrowDefault(request.companyId());
        Recruiter recruiter = new Recruiter(null, telegramUser, company);
        return recruiterMapper.entityToResponse(recruiterRepository.save(recruiter));
    }
}
