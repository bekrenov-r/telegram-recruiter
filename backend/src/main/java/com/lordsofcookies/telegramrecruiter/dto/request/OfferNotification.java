package com.lordsofcookies.telegramrecruiter.dto.request;

import java.util.List;
import java.util.UUID;

public record OfferNotification(
        UUID offerId,
        String jobTitle,
        List<String> recipientsIds
) { }
