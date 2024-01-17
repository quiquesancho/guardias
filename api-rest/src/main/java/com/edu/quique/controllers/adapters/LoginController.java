package com.edu.quique.controllers.adapters;

import com.edu.quique.api.LoginApi;
import com.edu.quique.api.model.LoginRequest;
import com.edu.quique.api.model.LoginResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class LoginController implements LoginApi {
  private final AuthenticationManager authenticationManager;

  @Override
  public ResponseEntity<LoginResponse> login(LoginRequest loginRequest) {
    authenticateUser(loginRequest.getUsername(), loginRequest.getPassword());
    var res = new LoginResponse();
    res.setToken(SecurityContextHolder.getContext().toString());
    return ResponseEntity.ok(res);
  }

  private void authenticateUser(String username, String password) {
    Authentication authentication =
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(username, password));
    SecurityContextHolder.getContext().setAuthentication(authentication);
  }
}
