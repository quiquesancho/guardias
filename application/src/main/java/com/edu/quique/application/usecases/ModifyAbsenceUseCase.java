package com.edu.quique.application.usecases;

import com.edu.quique.application.domain.Absence;
import com.edu.quique.application.ports.in.services.AbsenceServicePort;
import com.edu.quique.application.ports.in.usecases.ModifyAbsenceUseCasePort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ModifyAbsenceUseCase implements ModifyAbsenceUseCasePort {
    private AbsenceServicePort absenceService;
    @Override
    public Absence execute(Absence absence) {
        return absenceService.modifyAbsence(absence);
    }
}
