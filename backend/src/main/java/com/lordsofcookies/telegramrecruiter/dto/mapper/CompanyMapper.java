package com.lordsofcookies.telegramrecruiter.dto.mapper;

import com.lordsofcookies.telegramrecruiter.dto.response.CompanyResponse;
import com.lordsofcookies.telegramrecruiter.entity.Company;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class CompanyMapper {
    public abstract CompanyResponse entityToResponse(Company entity);
}