package com.edu.quique.application.ports.in.services;

import com.edu.quique.application.domain.Absence;

import java.util.List;

public interface AbsenceServicePort {
  List<Absence> createAbsence(Absence absence);
}
