package com.edu.quique.controllers.adapters;

import com.edu.quique.api.TeachingHoursApi;
import com.edu.quique.api.model.TeachingHoursResponse;
import com.edu.quique.application.dto.TeachingHoursResponseDTO;
import com.edu.quique.application.ports.in.usecases.FindAllTeachingHoursGuardUseCasePort;
import com.edu.quique.controllers.mappers.TeachingHoursResponseMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Slf4j
public class TeachingHoursControllerAdapter implements TeachingHoursApi {
  private FindAllTeachingHoursGuardUseCasePort getTeachinHoursGuardUseCase;
  private TeachingHoursResponseMapper teachingHoursResponseMapper;

  @Override
  public ResponseEntity<TeachingHoursResponse> getTeachingHoursGuards() {
    log.info("GET /teachingHours");
    TeachingHoursResponseDTO responseDTO =
        TeachingHoursResponseDTO.builder()
            .teachingHours(getTeachinHoursGuardUseCase.execute())
            .build();
    return ResponseEntity.ok(teachingHoursResponseMapper.fromTeachingHoursResponseDTO(responseDTO));
  }
}
