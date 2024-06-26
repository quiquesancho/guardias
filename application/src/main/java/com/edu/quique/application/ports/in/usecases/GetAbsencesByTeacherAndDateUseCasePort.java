package com.edu.quique.application.ports.in.usecases;

import com.edu.quique.application.domain.Absence;

import java.time.LocalDate;
import java.util.List;

public interface GetAbsencesByTeacherAndDateUseCasePort {
    List<Absence> execute(String email, LocalDate absenceDate);
}
