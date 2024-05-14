package com.edu.quique.controllers.adapters;

import com.edu.quique.api.LoginApi;
import com.edu.quique.api.model.LoginRequest;
import com.edu.quique.api.model.LoginResponse;
import com.edu.quique.application.ports.in.usecases.GetOUsUseCasePort;
import com.edu.quique.application.ports.in.usecases.GetTeacherByEmailUseCasePort;
import com.edu.quique.controllers.config.JWTAuthtenticationConfig;
import com.edu.quique.controllers.mappers.TeacherMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@Slf4j
public class LoginControllerAdapter implements LoginApi {
  private final AuthenticationManager authenticationManager;
  private final GetOUsUseCasePort getOUsUseCase;
  private final JWTAuthtenticationConfig jwtAuthtenticationConfig;

  @Override
  public ResponseEntity<LoginResponse> login(LoginRequest loginRequest) {
    log.info("POST /login Username: {}", loginRequest.getUsername());
    var roles = getOUsUseCase.execute(loginRequest.getUsername());
    authenticateUser(loginRequest.getUsername(), loginRequest.getPassword(), roles);
    var res = new LoginResponse();
    res.setToken(jwtAuthtenticationConfig.getJWTToken(loginRequest.getUsername(), roles));
    return ResponseEntity.ok(res);
  }

  private void authenticateUser(String username, String password, List<String> authorities) {
    Authentication authentication =
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                username, password, setAuthorities(authorities)));
    SecurityContextHolder.getContext().setAuthentication(authentication);
  }

  private List<SimpleGrantedAuthority> setAuthorities(List<String> authorities) {
    return authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
  }
}
