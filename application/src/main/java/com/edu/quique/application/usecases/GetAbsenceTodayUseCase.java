package com.edu.quique.application.usecases;

import com.edu.quique.application.domain.Absence;
import com.edu.quique.application.ports.in.services.AbsenceServicePort;
import com.edu.quique.application.ports.in.usecases.GetAbsenceTodayUseCasePort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetAbsenceTodayUseCase implements GetAbsenceTodayUseCasePort {
  private AbsenceServicePort absenceService;

  @Override
  public List<Absence> execute() {
    return absenceService.findAbsenseToday();
  }
}
