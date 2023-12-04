package com.edu.quique.application.ports.out;

import com.edu.quique.application.domain.Teacher;
import com.edu.quique.application.domain.TimetableGroup;
import com.edu.quique.application.utils.TimeInterval;

public interface TimetableGroupRepositoryPort {
  TimetableGroup findByDayOfWeekAndStartHourAndEndHourAndTeacher(String dayOfWeek, TimeInterval hours, Teacher teacher);
  TimetableGroup save(TimetableGroup timetableGroup);
  void deleteAll();
}