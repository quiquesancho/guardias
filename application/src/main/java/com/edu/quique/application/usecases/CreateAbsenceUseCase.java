package com.edu.quique.application.usecases;

import com.edu.quique.application.domain.Absence;
import com.edu.quique.application.ports.in.services.AbsenceServicePort;
import com.edu.quique.application.ports.in.usecases.CreateAbsenceUseCasePort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CreateAbsenceUseCase implements CreateAbsenceUseCasePort {
  private AbsenceServicePort absenceService;

  @Override
  public List<Absence> execute(Absence absence) {
    return absenceService.createAbsence(absence);
  }
}
