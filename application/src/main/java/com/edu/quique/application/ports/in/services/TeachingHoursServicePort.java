package com.edu.quique.application.ports.in.services;

import com.edu.quique.application.domain.TeachingHour;

import java.util.List;

public interface TeachingHoursServicePort {
  List<TeachingHour> findAllTeachingHoursGuard();
  TeachingHour save(TeachingHour teachingHour);
  void deleteAll();
}
