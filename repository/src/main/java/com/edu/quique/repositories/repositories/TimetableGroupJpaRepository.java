package com.edu.quique.repositories.repositories;

import com.edu.quique.repositories.models.TeacherMO;
import com.edu.quique.repositories.models.TimetableGroupMO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalTime;

public interface TimetableGroupJpaRepository extends JpaRepository<TimetableGroupMO, Long> {
  TimetableGroupMO findByDayOfWeekAndStartHourAndEndHourAndTeacher(
      String dayOfWeek, LocalTime startHour, LocalTime endHour, TeacherMO teacher);
}
