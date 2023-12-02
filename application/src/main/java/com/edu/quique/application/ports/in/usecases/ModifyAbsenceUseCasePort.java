package com.edu.quique.application.ports.in.usecases;

import com.edu.quique.application.domain.Absence;

public interface ModifyAbsenceUseCasePort {
    Absence execute(Absence absence);
}
