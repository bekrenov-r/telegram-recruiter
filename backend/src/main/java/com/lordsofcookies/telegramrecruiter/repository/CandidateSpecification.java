package com.lordsofcookies.telegramrecruiter.repository;

import com.lordsofcookies.telegramrecruiter.entity.Candidate;
import com.lordsofcookies.telegramrecruiter.entity.Offer;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class CandidateSpecification {
    public static Specification<Candidate> candidateFitsToOffer(Offer offer){
        return (root, query, criteriaBuilder) -> {
            Predicate preferredLocationMatches = criteriaBuilder.equal(
                    criteriaBuilder.lower(root.get("preferredLocationVoivodeship")),
                    offer.getCity().trim().toLowerCase()
            );
            Predicate atLeastOnePreferredTechnologyMatches = offer.getTechnologies().stream()
                    .map(tech -> criteriaBuilder.isMember(tech, root.get("preferredTechnologies")))
                    .reduce(criteriaBuilder::or)
                    .orElse(criteriaBuilder.conjunction());
            Predicate preferredLevelMatches = criteriaBuilder.isMember(offer.getLevel(), root.get("preferredLevels"));
            Predicate preferredWorkModeMatches = criteriaBuilder.isMember(offer.getWorkMode(), root.get("preferredWorkModes"));
            return criteriaBuilder.and(preferredLocationMatches, atLeastOnePreferredTechnologyMatches, preferredLevelMatches, preferredWorkModeMatches);
        };
    }
}
