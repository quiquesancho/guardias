package com.edu.quique.controllers.adapters;

import com.edu.quique.api.AbsenceApi;
import com.edu.quique.api.model.AbsenceRequest;
import com.edu.quique.api.model.AbsenceResponse;
import com.edu.quique.application.dto.AbsenceResponseDTO;
import com.edu.quique.application.ports.in.usecases.CreateAbsenceUseCasePort;
import com.edu.quique.controllers.mappers.AbsenceMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Slf4j
public class AbsenceControllerAdapter implements AbsenceApi {
  private CreateAbsenceUseCasePort createAbsenceUseCase;
  private AbsenceMapper absenceMapper;

  @Override
  public ResponseEntity<AbsenceResponse> createAbsence(AbsenceRequest absenceRequest) {
    log.info("POST /absence");
    AbsenceResponseDTO responseDTO =
        AbsenceResponseDTO.builder()
            .absences(
                createAbsenceUseCase.execute(absenceMapper.fromAbsenceRequest(absenceRequest)))
            .build();
    return ResponseEntity.status(HttpStatus.OK).body(absenceMapper.toAbsenceResponse(responseDTO));
  }
}
