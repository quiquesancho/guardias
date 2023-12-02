package com.edu.quique.application.service;

import com.edu.quique.application.domain.Absence;
import com.edu.quique.application.domain.Teacher;
import com.edu.quique.application.exceptions.AbsenceAlreadyExistsException;
import com.edu.quique.application.exceptions.AbsenceAlreadyInCurseException;
import com.edu.quique.application.exceptions.AbsenceNotFoundException;
import com.edu.quique.application.ports.in.services.AbsenceServicePort;
import com.edu.quique.application.ports.in.services.TeacherServicePort;
import com.edu.quique.application.ports.out.AbsenceRepositoryPort;
import com.edu.quique.application.utils.TimeInterval;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
    Teacher teacher = teacherService.findByEmail(absence.getAbsentTeacher().getEmail());
    absence.setAbsentTeacher(teacher);
    throwExceptionIfExistsAbsence(absence);
    List<Absence> absencesList = new ArrayList<>();
    log.info(teacher.toString());
    List<TimeInterval> timeIntervalAbsences =
        TimeInterval.generateIntervals(absence.getTimeInterval());
    timeIntervalAbsences.forEach(
        timeInterval ->
            absencesList.add(
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

  @Override
  public void deleteAbsence(Long id) {
    Absence absenceToDelete =
        absenceRepository
            .findById(id)
            .orElseThrow(
                () -> new AbsenceNotFoundException("Absence with id: " + id + " not found."));
    throwExceptionIfInCurseAbsence(absenceToDelete);
  }

  private void throwExceptionIfExistsAbsence(Absence absence) {
    List<Absence> absencesList =
        absenceRepository.findByAbsenceDateAndStartHourOrAbsenceDateAndEndHourAndAbsentTeacher(
            absence);
    if (!absencesList.isEmpty())
      throw new AbsenceAlreadyExistsException(
          String.format(
              "Already exists absences for this date: %s and between the hours %s",
              absence.getAbsenceDate().format(DateTimeFormatter.ISO_DATE),
              absence.getTimeInterval().toStringHours()));
  }

  private void throwExceptionIfInCurseAbsence(Absence absence) {
    if (LocalTime.now().isAfter(absence.getTimeInterval().getStartHour()))
      throw new AbsenceAlreadyInCurseException(
          "Absence is already in curse, start at: " + absence.getTimeInterval().getStartHour());
  }
}
