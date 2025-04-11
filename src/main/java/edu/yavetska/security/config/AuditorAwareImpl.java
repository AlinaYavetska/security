package edu.yavetska.security.config;

/*
@author   Admin
@project   security
@class  AuditorAwareImpl
@version  1.0.0
@since 11.04.2025 - 18.28
*/

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {


    @Override
    public Optional<String> getCurrentAuditor() {
//       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//       UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//       String username = userDetails.getUsername();
        return Optional.of(System.getProperty("user.name"));
    }
}
