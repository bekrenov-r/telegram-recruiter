package com.lordsofcookies.telegramrecruiter.dto.mapper;

import com.lordsofcookies.telegramrecruiter.dto.request.OfferRequest;
import com.lordsofcookies.telegramrecruiter.dto.response.OfferResponse;
import com.lordsofcookies.telegramrecruiter.entity.Company;
import com.lordsofcookies.telegramrecruiter.entity.Offer;
import com.lordsofcookies.telegramrecruiter.repository.CompanyRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Mapper(componentModel = "spring")
public abstract class OfferMapper {
    @Autowired
    protected CompanyRepository companyRepository;

    public abstract OfferResponse entityToResponse(Offer entity);

    @Mapping(source = "companyId", target = "company", qualifiedByName = "mapCompany")
    public abstract Offer requestToEntity(OfferRequest request);

    @Named("mapCompany")
    protected Company mapCompany(UUID companyId){
        return companyRepository.findByIdOrThrowDefault(companyId);
    }
}
