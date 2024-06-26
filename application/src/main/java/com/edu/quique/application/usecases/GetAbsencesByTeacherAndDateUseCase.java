package com.edu.quique.application.usecases;

import com.edu.quique.application.domain.Absence;
import com.edu.quique.application.ports.in.services.AbsenceServicePort;
import com.edu.quique.application.ports.in.usecases.GetAbsencesByTeacherAndDateUseCasePort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class GetAbsencesByTeacherAndDateUseCase implements GetAbsencesByTeacherAndDateUseCasePort {
    private AbsenceServicePort absenceService;
    @Override
    public List<Absence> execute(String email, LocalDate absenceDate) {
        return absenceService.findAbsenseByTeacherAndDate(email, absenceDate);
    }
}
