package com.lordsofcookies.telegramrecruiter.security.auth;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtAuthenticationToken extends AbstractAuthenticationToken {
    private final String subject;
    private final String jwt;

    public JwtAuthenticationToken(Collection<? extends GrantedAuthority> authorities, String subject, String jwt) {
        super(authorities);
        this.subject = subject;
        this.jwt = jwt;
        this.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return jwt;
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
