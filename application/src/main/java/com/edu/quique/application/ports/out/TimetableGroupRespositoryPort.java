package com.edu.quique.application.ports.out;

import com.edu.quique.application.domain.TimetableGroup;

public interface TimetableGroupRespositoryPort {
  TimetableGroup save(TimetableGroup timetableGroup);
  void deleteAll();
}