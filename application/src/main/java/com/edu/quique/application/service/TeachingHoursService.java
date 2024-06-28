package com.edu.quique.application.service;

import com.edu.quique.application.domain.TeachingHour;
import com.edu.quique.application.ports.in.services.TeachingHoursServicePort;
import com.edu.quique.application.ports.out.TeachingHoursRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

import static com.edu.quique.application.utils.AppConstants.BREAKS;

@Service
@AllArgsConstructor
@Transactional
public class TeachingHoursService implements TeachingHoursServicePort {
  private TeachingHoursRepositoryPort teachingHoursPort;

  @Override
  public List<TeachingHour> findAllTeachingHoursGuard() {
    var teachingHoursList = teachingHoursPort.findAllTeachingHoursGuard();
    teachingHoursList.addAll(BREAKS);
    Collections.sort(teachingHoursList);
    return teachingHoursList;
  }

  @Override
  public TeachingHour save(TeachingHour teachingHour) {
    return teachingHoursPort.save(teachingHour);
  }

  @Override
  public void deleteAll() {
    teachingHoursPort.deleteAll();
  }
}
