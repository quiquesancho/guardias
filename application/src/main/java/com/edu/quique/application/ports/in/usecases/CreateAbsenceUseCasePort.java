package com.edu.quique.application.ports.in.usecases;

import com.edu.quique.application.domain.Absence;

import java.util.List;

public interface CreateAbsenceUseCasePort {
    List<Absence> execute(Absence absence);
}
