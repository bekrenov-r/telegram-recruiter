package com.lordsofcookies.telegramrecruiter.dto.mapper;

import com.lordsofcookies.telegramrecruiter.dto.response.OfferResponse;
import com.lordsofcookies.telegramrecruiter.entity.Offer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class OfferMapper {
    public abstract OfferResponse entityToResponse(Offer entity);
}
