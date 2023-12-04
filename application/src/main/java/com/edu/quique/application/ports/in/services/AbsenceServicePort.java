package com.edu.quique.application.ports.in.services;

import com.edu.quique.application.domain.Absence;

import java.util.List;

public interface AbsenceServicePort {
  Absence findById(Long id);
  List<Absence> findAbsencesNotRegistred();
  List<Absence> createAbsence(Absence absence);
  void deleteAbsence(Long id);
  Absence modifyAbsence(Absence absence);
}
