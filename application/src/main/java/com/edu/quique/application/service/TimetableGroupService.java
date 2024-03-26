package com.edu.quique.application.service;

import com.edu.quique.application.domain.Teacher;
import com.edu.quique.application.domain.TimetableGroup;
import com.edu.quique.application.ports.in.services.TimetableGroupServicePort;
import com.edu.quique.application.ports.out.TimetableGroupRepositoryPort;
import com.edu.quique.application.utils.TimeInterval;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class TimetableGroupService implements TimetableGroupServicePort {
  private TimetableGroupRepositoryPort timetableGroupRepositoryPort;

  @Override
  public TimetableGroup findByDayOfWeekAndStartHourAndEndHourAndTeacher(
      String dayOfWeek, TimeInterval hours, Teacher teacher) {
    return timetableGroupRepositoryPort.findByDayOfWeekAndStartHourAndEndHourAndTeacher(
        dayOfWeek, hours, teacher);
  }

  @Override
  public TimetableGroup save(TimetableGroup timetableGroup) {
    return timetableGroupRepositoryPort.save(timetableGroup);
  }

  @Override
  public void deleteAll() {
    timetableGroupRepositoryPort.deleteAll();
  }
}
