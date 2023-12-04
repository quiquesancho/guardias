package com.edu.quique.repositories.repositories;

import com.edu.quique.repositories.models.AbsenceMO;
import com.edu.quique.repositories.models.TeacherMO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface AbsenceJpaRepository extends JpaRepository<AbsenceMO, Long> {
  boolean existsByAbsenceDateAndStartHourAndEndHourAndAbsentTeacher_Email(
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

  @Query(
      value =
          "SELECT a.* FROM absence a "
              + "INNER JOIN teachers t ON t.teacher_id = a.teacher_id "
              + "INNER JOIN timetable_group tg ON tg.day_of_week = a.day_of_week "
              + "AND a.start_hour = tg.start_hour AND a.end_hour = tg.end_hour AND t.teacher_id = tg.teacher_id "
              + "WHERE a.absence_date = current_date AND NOT EXISTS "
              + "(SELECT 1 FROM registry r WHERE r.absence_id = a.absence_id)",
      nativeQuery = true)
  List<AbsenceMO> findAbsencesForTodayWithoutRegistry();
}
