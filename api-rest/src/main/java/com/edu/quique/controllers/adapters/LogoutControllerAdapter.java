package com.edu.quique.controllers.adapters;

import com.edu.quique.api.LogoutApi;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogoutControllerAdapter implements LogoutApi {
  @Override
  public ResponseEntity<Void> logout() {
    SecurityContextHolder.clearContext();
    return ResponseEntity.ok().build();
  }
}
