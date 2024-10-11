package com.edu.quique.application.schedule;

import com.edu.quique.application.domain.RegistryAbsence;
import com.edu.quique.application.domain.Teacher;
import com.edu.quique.application.domain.queryparams.TeacherQueryParams;
import com.edu.quique.application.ports.in.services.RegistryAbsenceServicePort;
import com.edu.quique.application.ports.in.services.TeacherServicePort;
import com.edu.quique.application.ports.out.RegistryAbsenceScheduleRespositoryPort;
import com.edu.quique.application.utils.DaysOfWeek;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.List;

import static com.edu.quique.application.utils.AppConstants.OCCUPATION_GUARD;

@Service
@AllArgsConstructor
@Slf4j
public class RegistryAbsenceSchedule implements RegistryAbsenceScheduleRespositoryPort {
  private TeacherServicePort teacherServicePort;
  private RegistryAbsenceServicePort registryAbsenceServicePort;
  private ApplicationEventPublisher publisher;

  @Override
  @Scheduled(cron = "*/10 * * * * *")
  public void sendEvent() {
    var teachers = getGuardTeachers();
    teachers.forEach(this::publishRegistryAbsence);
  }

  private List<Teacher> getGuardTeachers() {
    var queryParams =
        TeacherQueryParams.builder()
            .dayOfWeek(DaysOfWeek.getByOrder(LocalDate.now().getDayOfWeek().ordinal()).getDay())
            .instantNow(LocalTime.now())
            .occupation(OCCUPATION_GUARD)
            .build();
    return teacherServicePort.findAll(queryParams);
  }

  private List<RegistryAbsence> getRegistryAbsences(Teacher teacher) {
    return registryAbsenceServicePort.findByTeacherGuard(teacher);
  }

  private void publishRegistryAbsence(Teacher teacher) {
    var registryAbsenceList = getRegistryAbsences(teacher);
    if (isAvailableGuard(registryAbsenceList)) {
      publisher.publishEvent(RegistryAbsence.builder().teacherGuard(teacher).build());
    }
  }

  private boolean isAvailableGuard(List<RegistryAbsence> registryAbsenceList) {
    return registryAbsenceList.stream()
        .noneMatch(
            registryAbsence ->
                registryAbsence.isChecked()
                    && OffsetDateTime.now().isAfter(registryAbsence.getCheckGuard()));
  }
}
