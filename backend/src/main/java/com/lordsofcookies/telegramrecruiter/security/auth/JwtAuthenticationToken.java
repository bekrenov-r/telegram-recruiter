package com.lordsofcookies.telegramrecruiter.security.auth;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtAuthenticationToken extends AbstractAuthenticationToken {
    private final String subject;

    public JwtAuthenticationToken(Collection<? extends GrantedAuthority> authorities, String subject) {
        super(authorities);
        this.subject = subject;
        this.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return subject;
    }

    @Override
    public String getName(){
        return subject;
    }
}
