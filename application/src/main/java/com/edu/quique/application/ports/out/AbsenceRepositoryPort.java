package com.edu.quique.application.ports.out;

import com.edu.quique.application.domain.Absence;

import java.util.List;

public interface AbsenceRepositoryPort {
  List<Absence> findByAbsenceDateAndStartHourOrAbsenceDateAndEndHourAndAbsentTeacher(Absence absence);
  Absence save(Absence absence);
}
