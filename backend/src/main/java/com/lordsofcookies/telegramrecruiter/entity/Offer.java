package com.lordsofcookies.telegramrecruiter.entity;

import com.lordsofcookies.telegramrecruiter.enums.Level;
import com.lordsofcookies.telegramrecruiter.enums.Position;
import com.lordsofcookies.telegramrecruiter.enums.Technology;
import com.lordsofcookies.telegramrecruiter.enums.WorkMode;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "offer")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private String description;

    @Column(name = "salary_range_bottom")
    private Integer salaryRangeBottom;

    @Column(name = "salary_range_top")
    private Integer salaryRangeTop;

    @Column
    private String city;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "position")
    @Enumerated(EnumType.STRING)
    private Position position;

    @Column(name = "level")
    @Enumerated(EnumType.STRING)
    private Level level;

    @Column(name = "work_mode")
    @Enumerated(EnumType.STRING)
    private WorkMode workMode;

    @ElementCollection(targetClass = Technology.class, fetch = FetchType.LAZY)
    @CollectionTable(name = "offer_technology", joinColumns = @JoinColumn(name = "offer_id"))
    @Column(name = "technology")
    @Enumerated(EnumType.STRING)
    private Set<Technology> technologies;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToOne
    @JoinColumn(name = "created_by_recruiter_id")
    private Recruiter createdByRecruiter;
}
