package com.edu.quique.repositories.repositories;

import com.edu.quique.repositories.models.AbsenceMO;
import com.edu.quique.repositories.models.TeacherMO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface AbsenceJpaRepository extends JpaRepository<AbsenceMO, Long>, JpaSpecificationExecutor<AbsenceMO> {
  boolean existsByAbsenceDateAndStartHourAndEndHourAndAbsentTeacherEmail(
      LocalDate absenceDate, LocalTime startHour, LocalTime endHour, String email);

  AbsenceMO findByAbsenceId(Long id);

  List<AbsenceMO>
      findByAbsenceDateAndStartHourAndAbsentTeacherOrAbsenceDateAndEndHourAndAbsentTeacher(
          LocalDate absenceDate,
          LocalTime startHour,
          TeacherMO teacherMO,
          LocalDate absenceDate2,
          LocalTime endHour,
          TeacherMO teacherAbsent);
}
