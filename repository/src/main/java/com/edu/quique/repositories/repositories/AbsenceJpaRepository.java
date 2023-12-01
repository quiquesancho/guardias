package com.edu.quique.repositories.repositories;

import com.edu.quique.repositories.models.AbsenceMO;
import com.edu.quique.repositories.models.TeacherMO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface AbsenceJpaRepository extends JpaRepository<AbsenceMO, Long> {
  List<AbsenceMO> findByAbsenceDateAndStartHourOrAbsenceDateAndEndHourAndAbsentTeacher(
      LocalDate absenceDate,
      LocalTime startHour,
      LocalDate absenceDate2,
      LocalTime endHour,
      TeacherMO teacherAbsent);
}
