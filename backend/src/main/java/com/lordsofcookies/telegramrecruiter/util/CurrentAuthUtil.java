package com.lordsofcookies.telegramrecruiter.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class CurrentAuthUtil {
    public static Authentication getCurrentAuth(){
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
