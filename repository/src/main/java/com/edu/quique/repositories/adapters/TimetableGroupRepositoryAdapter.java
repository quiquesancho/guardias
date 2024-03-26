package com.edu.quique.repositories.adapters;

import com.edu.quique.application.domain.Teacher;
import com.edu.quique.application.domain.TimetableGroup;
import com.edu.quique.application.ports.out.TimetableGroupRepositoryPort;
import com.edu.quique.application.utils.TimeInterval;
import com.edu.quique.repositories.mappers.TeacherMOMapper;
import com.edu.quique.repositories.mappers.TimetableGroupMOMapper;
import com.edu.quique.repositories.repositories.TimetableGroupJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TimetableGroupRepositoryAdapter implements TimetableGroupRepositoryPort {
  private TimetableGroupJpaRepository timetableGroupJpaRepository;
  private TimetableGroupMOMapper timetableGroupMOMapper;
  private TeacherMOMapper teacherMOMapper;

  @Override
  public TimetableGroup findByDayOfWeekAndStartHourAndEndHourAndTeacher(
      String dayOfWeek, TimeInterval hours, Teacher teacher) {
    return timetableGroupMOMapper.toTimetableGroup(
        timetableGroupJpaRepository.findByDayOfWeekAndStartHourAndEndHourAndTeacher(
            dayOfWeek,
            hours.getStartHour(),
            hours.getEndHour(),
            teacherMOMapper.toTeacherMO(teacher)));
  }

  @Override
  public TimetableGroup save(TimetableGroup timetableGroup) {
    return timetableGroupMOMapper.toTimetableGroup(
        timetableGroupJpaRepository.save(
            timetableGroupMOMapper.toTimetableGroupMO(timetableGroup)));
  }

  @Override
  public void deleteAll() {
    timetableGroupJpaRepository.deleteAll();
  }
}
