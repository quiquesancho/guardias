package com.edu.quique.application.service;

import com.edu.quique.application.domain.Absence;
import com.edu.quique.application.domain.queryparams.AbsenceQueryParams;
import com.edu.quique.application.exceptions.AbsenceAlreadyExistsException;
import com.edu.quique.application.exceptions.AbsenceCannotBeModifiedOrDeletedException;
import com.edu.quique.application.exceptions.AbsenceNotFoundException;
import com.edu.quique.application.ports.in.services.AbsenceServicePort;
import com.edu.quique.application.ports.in.services.TeacherServicePort;
import com.edu.quique.application.ports.in.services.TimetableGroupServicePort;
import com.edu.quique.application.ports.out.AbsenceRepositoryPort;
import com.edu.quique.application.utils.DaysOfWeek;
import com.edu.quique.application.utils.TimeInterval;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
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
  private TimetableGroupServicePort timetableGroupService;

  @Override
  public Absence findById(Long id) {
    return absenceRepository
        .findById(id)
        .orElseThrow(() -> new AbsenceNotFoundException("Absence with id: " + id + " not found."));
  }

  @Override
  public List<Absence> findAbsenseToday() {
    return this.findAll(
        AbsenceQueryParams.builder().absenceDate(LocalDate.now()).build());
  }

  @Override
  public List<Absence> findAll(AbsenceQueryParams params) {
    return absenceRepository.findAll(params);
  }

  @Override
  public List<Absence> findAbsenseByTeacherAndDate(String email, LocalDate date) {
    return this.findAll(
        AbsenceQueryParams.builder().absenceDate(date).email(email).build());
  }

  @Override
  public List<Absence> createAbsence(Absence absence) {
    var teacher = teacherService.findByEmail(absence.getAbsentTeacher().getEmail());
    absence.setAbsentTeacher(teacher);
    String dayOfWeek = DaysOfWeek.valueOf(absence.getAbsenceDate().getDayOfWeek().name()).getDay();
    checkAbsenceIsModifiable(absence);
    checkIfExistsAbsence(absence);
    List<Absence> absencesList = new ArrayList<>();
    List<TimeInterval> timeIntervalAbsences =
        TimeInterval.generateIntervals(absence.getTimeInterval());
    timeIntervalAbsences.forEach(
        timeInterval ->
            absencesList.add(
                absenceRepository.save(
                    Absence.builder()
                        .dayOfWeek(dayOfWeek)
                        .absenceDate(absence.getAbsenceDate())
                        .timeInterval(timeInterval)
                        .absentTeacher(absence.getAbsentTeacher())
                        .timetableGroup(
                            timetableGroupService.findByDayOfWeekAndStartHourAndEndHourAndTeacher(
                                dayOfWeek, timeInterval, absence.getAbsentTeacher()))
                        .isAssigned(Boolean.FALSE)
                        .build())));
    return absencesList;
  }

  @Override
  public void deleteAbsence(Long id) {
    var absenceToDelete = findById(id);
    checkAbsenceIsModifiable(absenceToDelete);
    absenceRepository.deleteAbsence(absenceToDelete);
  }

  @Override
  public Absence modifyAbsence(Absence absence) {
    var absenceToModify = findById(absence.getAbsenceId());
    checkAbsenceIsModifiable(absenceToModify);
    checkAbsenceIsModifiable(absence);
    checkIfExistsAbsenceInThisDateAndHours(absence);
    absenceToModify.setAbsenceDate(absence.getAbsenceDate());
    absenceToModify.setTimeInterval(absence.getTimeInterval());
    return absenceRepository.save(absenceToModify);
  }

  private void checkIfExistsAbsenceInThisDateAndHours(Absence absence) {
    if (absenceRepository.existsByAbsenceDateAndStartHourAndEndHourAndAbsentTeacherEmail(absence))
      throw new AbsenceAlreadyExistsException(
          String.format(
              "Already exists absences for this date: %s and between the hours %s",
              absence.getAbsenceDate().format(DateTimeFormatter.ISO_DATE),
              absence.getTimeInterval().toStringHours()));
  }

  private void checkIfExistsAbsence(Absence absence) {
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

  private void checkAbsenceIsModifiable(Absence absence) {
    var today = LocalDate.now();
    var now = LocalTime.now();
    if (absence.getAbsenceDate().isBefore(today)
        || (absence.getAbsenceDate().isEqual(today)
            && absence.getTimeInterval().getStartHour().isBefore(now))) {
      throw new AbsenceCannotBeModifiedOrDeletedException("Absence cannot be modified or deleted.");
    }
  }
}
