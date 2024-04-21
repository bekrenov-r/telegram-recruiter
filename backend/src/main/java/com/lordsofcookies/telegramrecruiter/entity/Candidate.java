package com.lordsofcookies.telegramrecruiter.entity;

import com.lordsofcookies.telegramrecruiter.enums.Level;
import com.lordsofcookies.telegramrecruiter.enums.Position;
import com.lordsofcookies.telegramrecruiter.enums.Technology;
import com.lordsofcookies.telegramrecruiter.enums.WorkMode;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "candidate")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Candidate {
    @Id
    @Column(name = "telegram_id")
    private String telegramId;

    @OneToOne(targetEntity = TelegramUser.class)
    @JoinColumn(name = "telegram_id", referencedColumnName = "telegram_id")
    @MapsId
    private TelegramUser telegramUser;

    @Column(name = "enable_offer_notifications")
    private boolean enableOfferNotifications;

    @Column(name = "preferred_location_voivodeship")
    private String preferredLocationVoivodeship;

    @ElementCollection(targetClass = Technology.class, fetch = FetchType.LAZY)
    @CollectionTable(name = "candidate_technology", joinColumns = @JoinColumn(name = "candidate_id"))
    @Column(name = "technology")
    @Enumerated(EnumType.STRING)
    private Set<Technology> preferredTechnologies;

    @ElementCollection(targetClass = Position.class, fetch = FetchType.LAZY)
    @CollectionTable(name = "candidate_position", joinColumns = @JoinColumn(name = "candidate_id"))
    @Column(name = "position")
    @Enumerated(EnumType.STRING)
    private Set<Position> preferredPositions;

    @ElementCollection(targetClass = Level.class, fetch = FetchType.LAZY)
    @CollectionTable(name = "candidate_level", joinColumns = @JoinColumn(name = "candidate_id"))
    @Column(name = "level")
    @Enumerated(EnumType.STRING)
    private Set<Level> preferredLevels;

    @ElementCollection(targetClass = WorkMode.class, fetch = FetchType.LAZY)
    @CollectionTable(name = "candidate_work_mode", joinColumns = @JoinColumn(name = "candidate_id"))
    @Column(name = "work_mode")
    @Enumerated(EnumType.STRING)
    private Set<WorkMode> preferredWorkModes;
}
