package com.edu.quique.application.ports.out;

import com.edu.quique.application.domain.Absence;

import java.util.List;
import java.util.Optional;

public interface AbsenceRepositoryPort {
  Optional<Absence> findById(Long id);
  boolean existsByAbsenceDateAndStartHourAndEndHourAndAbsentTeacher_Email(Absence absence);
  List<Absence> findByAbsenceDateAndStartHourOrAbsenceDateAndEndHourAndAbsentTeacher(Absence absence);
  Absence save(Absence absence);
  void deleteAbsence(Absence absence);
}
