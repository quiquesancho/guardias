package com.edu.quique.application.ports.in.services;

import com.edu.quique.application.domain.Teacher;
import com.edu.quique.application.domain.TimetableGroup;
import com.edu.quique.application.utils.TimeInterval;

public interface TimetableGroupServicePort {
    TimetableGroup findByDayOfWeekAndStartHourAndEndHourAndTeacher(String dayOfWeek, TimeInterval hours, Teacher teacher);
    TimetableGroup save(TimetableGroup timetableGroup);
    void deleteAll();
}