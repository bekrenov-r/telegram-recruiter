package com.lordsofcookies.telegramrecruiter.entity;


import com.lordsofcookies.telegramrecruiter.enums.Level;
import com.lordsofcookies.telegramrecruiter.enums.Position;
import com.lordsofcookies.telegramrecruiter.enums.Technology;
import com.lordsofcookies.telegramrecruiter.enums.WorkMode;
import com.lordsofcookies.telegramrecruiter.security.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "telegram_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TelegramUser implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "telegram_id", unique = true)
    private String telegramId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "telegram_username")
    private String telegramUsername;

    @Column(name = "preferred_location_voivodeship")
    private String preferredLocationVoivodeship;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @ElementCollection(targetClass = Technology.class, fetch = FetchType.LAZY)
    @CollectionTable(name = "user_technology", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "technology")
    @Enumerated(EnumType.STRING)
    private Set<Technology> preferredTechnologies;

    @ElementCollection(targetClass = Technology.class, fetch = FetchType.LAZY)
    @CollectionTable(name = "user_position", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "position")
    @Enumerated(EnumType.STRING)
    private Set<Position> preferredPositions;

    @ElementCollection(targetClass = Technology.class, fetch = FetchType.LAZY)
    @CollectionTable(name = "user_level", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "level")
    @Enumerated(EnumType.STRING)
    private Set<Level> preferredLevels;

    @ElementCollection(targetClass = WorkMode.class, fetch = FetchType.LAZY)
    @CollectionTable(name = "user_work_mode", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "work_mode")
    @Enumerated(EnumType.STRING)
    private Set<WorkMode> preferredWorkModes;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Set.of(role);
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return telegramId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}