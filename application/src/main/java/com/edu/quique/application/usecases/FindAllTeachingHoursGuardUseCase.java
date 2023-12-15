package com.edu.quique.application.usecases;

import com.edu.quique.application.domain.TeachingHour;
import com.edu.quique.application.ports.in.services.TeachingHoursServicePort;
import com.edu.quique.application.ports.in.usecases.FindAllTeachingHoursGuardUseCasePort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FindAllTeachingHoursGuardUseCase implements FindAllTeachingHoursGuardUseCasePort {
  private TeachingHoursServicePort teachingHoursService;

  @Override
  public List<TeachingHour> execute() {
    return teachingHoursService.findAllTeachingHoursGuard();
  }
}
