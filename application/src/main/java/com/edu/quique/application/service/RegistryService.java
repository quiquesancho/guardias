package com.edu.quique.application.service;

import com.edu.quique.application.domain.Absence;
import com.edu.quique.application.domain.Registry;
import com.edu.quique.application.domain.TimetableGroup;
import com.edu.quique.application.ports.in.services.AbsenceServicePort;
import com.edu.quique.application.ports.in.services.RegistryServicePort;
import com.edu.quique.application.ports.in.services.TimetableGroupServicePort;
import com.edu.quique.application.ports.out.RegistryRepositoryPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
@Transactional
@Slf4j
public class RegistryService implements RegistryServicePort {
  private RegistryRepositoryPort registryRepositoryPort;
  private AbsenceServicePort absenceService;
  private TimetableGroupServicePort timetableGroupService;

  @Override
  public Registry save(Registry registry) {
    return registryRepositoryPort.save(registry);
  }

  @Override
  @Scheduled(cron = "0 10-59/5 8-18 ? * MON-FRI")
  public void createRegistries() {
    List<Absence> absenceToRegistry = absenceService.findAbsencesNotRegistred();
    absenceToRegistry.stream()
        .map(
            absence -> {
              TimetableGroup timetableGroup =
                  timetableGroupService.findByDayOfWeekAndStartHourAndEndHourAndTeacher(
                      absence.getDayOfWeek(),
                      absence.getTimeInterval(),
                      absence.getAbsentTeacher());
              return Registry.builder()
                  .absence(absence)
                  .timetableGroup(timetableGroup)
                  .isAssigned(Boolean.FALSE)
                  .build();
            })
        .forEach(this::save);
  }
}
