package com.edu.quique.application.usecases;

import com.edu.quique.application.ports.in.services.AbsenceServicePort;
import com.edu.quique.application.ports.in.usecases.DeleteAbsenceUseCasePort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteAbsenceUseCase implements DeleteAbsenceUseCasePort {
  private AbsenceServicePort absenceService;

  @Override
  public void execute(Long id) {
    absenceService.deleteAbsence(id);
  }
}
