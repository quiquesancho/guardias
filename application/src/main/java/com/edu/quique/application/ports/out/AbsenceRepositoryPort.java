package com.edu.quique.application.ports.out;

import com.edu.quique.application.domain.Absence;

public interface AbsenceRepositoryPort {
  Absence save(Absence absence);
}
