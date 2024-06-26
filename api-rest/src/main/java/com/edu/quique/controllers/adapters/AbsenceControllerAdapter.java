package com.edu.quique.controllers.adapters;

import com.edu.quique.api.AbsenceApi;
import com.edu.quique.api.model.Absence;
import com.edu.quique.api.model.AbsenceRequest;
import com.edu.quique.api.model.AbsenceResponse;
import com.edu.quique.application.dto.AbsenceResponseDTO;
import com.edu.quique.application.ports.in.usecases.*;
import com.edu.quique.controllers.mappers.AbsenceMapper;
import com.edu.quique.controllers.utils.ContextUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@AllArgsConstructor
@Slf4j
public class AbsenceControllerAdapter implements AbsenceApi {
  private CreateAbsenceUseCasePort createAbsenceUseCase;
  private DeleteAbsenceUseCasePort deleteAbsenceUseCase;
  private ModifyAbsenceUseCasePort modifyAbsenceUseCase;
  private GetAbsenceTodayUseCasePort getAbsenceTodayUseCase;
  private GetAbsencesByTeacherAndDateUseCasePort getAbsencesByTeacherAndDateUseCase;
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

  @Override
  public ResponseEntity<Absence> modifyAbsence(AbsenceRequest absenceRequest) {
    log.info("PATCH /absence");
    return ResponseEntity.ok(
        absenceMapper.fromAbsence(
            modifyAbsenceUseCase.execute(absenceMapper.fromAbsenceRequest(absenceRequest))));
  }

  @Override
  public ResponseEntity<Void> deleteAbsence(Long absenceId) {
    log.info("DELETE /absence?absenceId={}", absenceId);
    deleteAbsenceUseCase.execute(absenceId);
    return ResponseEntity.ok().build();
  }

  @Override
  public ResponseEntity<AbsenceResponse> getAbsenceSummary() {
    log.info("GET /absence/absence-summary");
    var responseDTO =
        AbsenceResponseDTO.builder().absences(getAbsenceTodayUseCase.execute()).build();
    return ResponseEntity.ok(absenceMapper.toAbsenceResponse(responseDTO));
  }

  @Override
  public ResponseEntity<AbsenceResponse> getAbsencesByDate(LocalDate absenceDate) {
    log.info("GET /absence?absenceDate={}", absenceDate);
    var responseDTO =
        AbsenceResponseDTO.builder()
            .absences(getAbsencesByTeacherAndDateUseCase.execute(ContextUtils.getSubject(), absenceDate))
            .build();
    return ResponseEntity.ok(absenceMapper.toAbsenceResponse(responseDTO));
  }
}
