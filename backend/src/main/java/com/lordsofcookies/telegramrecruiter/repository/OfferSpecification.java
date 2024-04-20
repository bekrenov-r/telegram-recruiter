package com.lordsofcookies.telegramrecruiter.repository;

import com.lordsofcookies.telegramrecruiter.dto.request.OffersSearchCriteria;
import com.lordsofcookies.telegramrecruiter.entity.Offer;
import com.lordsofcookies.telegramrecruiter.enums.Level;
import com.lordsofcookies.telegramrecruiter.enums.Position;
import com.lordsofcookies.telegramrecruiter.enums.Technology;
import com.lordsofcookies.telegramrecruiter.enums.WorkMode;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class OfferSpecification {
    public static Specification<Offer> hasPositionIn(List<Position> positions){
        return (root, query, criteriaBuilder) -> root.get("position").in(positions);
    }

    public static Specification<Offer> hasTechnologyIn(List<Technology> technologies){
        return (root, query, criteriaBuilder) -> technologies.stream()
                .map(tech -> criteriaBuilder.isMember(tech, root.get("technologies")))
                .reduce(criteriaBuilder::or)
                .orElse(criteriaBuilder.conjunction());
    }

    public static Specification<Offer> hasLevelIn(List<Level> levels){
        return (root, query, criteriaBuilder) -> root.get("level").in(levels);
    }

    public static Specification<Offer> hasWorkModeIn(List<WorkMode> workModes){
        return (root, query, criteriaBuilder) -> root.get("workMode").in(workModes);
    }

    public static Specification<Offer> matchesSearchPattern(String searchPattern){
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("name")),
                        '%' + searchPattern.trim().toLowerCase() + '%'
                );
    }

    public static Specification<Offer> hasCity(String city){
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(criteriaBuilder.lower(root.get("city")), city.trim().toLowerCase());
    }

    public static Specification<Offer> fromSearchCriteria(OffersSearchCriteria criteria){
        List<Specification<Offer>> specifications = new ArrayList<>();
        if(criteria.technologies() != null){
            specifications.add(OfferSpecification.hasTechnologyIn(criteria.technologies()));
        }
        if(criteria.positions() != null){
            specifications.add(OfferSpecification.hasPositionIn(criteria.positions()));
        }
        if(criteria.levels() != null){
            specifications.add(OfferSpecification.hasLevelIn(criteria.levels()));
        }
        if(criteria.workModes() != null){
            specifications.add(OfferSpecification.hasWorkModeIn(criteria.workModes()));
        }
        if(criteria.searchPattern() != null){
            specifications.add(OfferSpecification.matchesSearchPattern(criteria.searchPattern()));
        }
        if(criteria.city() != null){
            specifications.add(OfferSpecification.hasCity(criteria.city()));
        }
        return Specification.allOf(specifications);
    }
}
