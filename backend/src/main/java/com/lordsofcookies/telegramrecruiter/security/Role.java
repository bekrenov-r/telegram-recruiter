package com.lordsofcookies.telegramrecruiter.security;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    CANDIDATE, RECRUITER, MODERATOR;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
