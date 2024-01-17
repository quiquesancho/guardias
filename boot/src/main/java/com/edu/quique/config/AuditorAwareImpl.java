package com.edu.quique.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {
  @Override
  public Optional<String> getCurrentAuditor() {
      String currentAud = "NotUserOnSecurityContext";
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      if (authentication != null) {
          currentAud = authentication.getName();
      }
      return Optional.of(currentAud);
  }
}
