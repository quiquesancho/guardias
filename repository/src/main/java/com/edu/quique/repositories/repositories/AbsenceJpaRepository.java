package com.edu.quique.repositories.repositories;

import com.edu.quique.repositories.models.AbsenceMO;
import com.edu.quique.repositories.models.TeacherMO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface AbsenceJpaRepository extends JpaRepository<AbsenceMO, Long> {
  boolean existsByAbsenceDateAndStartHourAndEndHourAndAbsentTeacherEmail(
      LocalDate absenceDate, LocalTime startHour, LocalTime endHour, String email);

  AbsenceMO findByAbsenceId(Long id);

  List<AbsenceMO> findByAbsenceDate(LocalDate date);

  List<AbsenceMO> findByAbsentTeacherAndAbsenceDate(TeacherMO absentTeacher, LocalDate absenceDate);

  List<AbsenceMO>
      findByAbsenceDateAndStartHourAndAbsentTeacherOrAbsenceDateAndEndHourAndAbsentTeacher(
          LocalDate absenceDate,
          LocalTime startHour,
          TeacherMO teacherMO,
          LocalDate absenceDate2,
          LocalTime endHour,
          TeacherMO teacherAbsent);
}
