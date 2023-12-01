package com.edu.quique.application.service;

import com.edu.quique.application.domain.Absence;
import com.edu.quique.application.domain.Teacher;
import com.edu.quique.application.ports.in.services.AbsenceServicePort;
import com.edu.quique.application.ports.in.services.TeacherServicePort;
import com.edu.quique.application.ports.out.AbsenceRepositoryPort;
import com.edu.quique.application.utils.TimeInterval;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
@Transactional
@Slf4j
public class AbsenceService implements AbsenceServicePort {
  private TeacherServicePort teacherService;
  private AbsenceRepositoryPort absenceRepository;

  @Override
  public List<Absence> createAbsence(Absence absence) {
    List<Absence> absencesList = new ArrayList<>();
    Teacher teacher = teacherService.findByEmail(absence.getAbsentTeacher().getEmail());
    List<TimeInterval> timeIntervalAbsences =
        TimeInterval.generateIntervals(absence.getTimeInterval());
    timeIntervalAbsences.forEach(
        timeInterval -> absencesList.add(
            absenceRepository.save(
                Absence.builder()
                    .dayOfWeek(absence.getDayOfWeek())
                    .absenceDate(absence.getAbsenceDate())
                    .timeInterval(timeInterval)
                    .absentTeacher(teacher)
                    .isAssigned(absence.getIsAssigned())
                    .build())));
    return absencesList;
  }

  private void throwExceptionIfExistsAbsence(Absence absence) {}
}
