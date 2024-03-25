package com.edu.quique.controllers.adapters;

import com.edu.quique.api.LoginApi;
import com.edu.quique.api.model.LoginRequest;
import com.edu.quique.api.model.LoginResponse;
import com.edu.quique.application.ports.in.usecases.GetOUsUseCasePort;
import com.edu.quique.application.ports.in.usecases.GetTeacherByEmailUseCasePort;
import com.edu.quique.controllers.mappers.TeacherMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Slf4j
public class LoginControllerAdapter implements LoginApi {
  private final AuthenticationManager authenticationManager;
  private final GetTeacherByEmailUseCasePort getTeacherByEmailUseCase;
  private final GetOUsUseCasePort getOUsUseCase;
  private final TeacherMapper teacherMapper;

  @Override
  public ResponseEntity<LoginResponse> login(LoginRequest loginRequest) {
    log.info("POST /login Username: {}", loginRequest.getUsername());
    authenticateUser(loginRequest.getUsername(), loginRequest.getPassword());
    var roles = getOUsUseCase.execute(loginRequest.getUsername());
    var teacher = getTeacherByEmailUseCase.execute(loginRequest.getUsername());
    var teacherResponse = teacherMapper.toTeacherModel(teacher);
    teacherResponse.setRole(roles);
    var res = new LoginResponse();
    res.setTeacher(teacherResponse);
    return ResponseEntity.ok(res);
  }

  private void authenticateUser(String username, String password) {
    Authentication authentication =
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(username, password));
    SecurityContextHolder.getContext().setAuthentication(authentication);
  }
}
