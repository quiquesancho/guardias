package com.edu.quique.application.ports.in.services;

import com.edu.quique.application.domain.RegistryAbsence;
import com.edu.quique.application.domain.Teacher;

import java.util.List;

public interface RegistryAbsenceServicePort {
    List<RegistryAbsence> findByTeacherGuard(Teacher teacher);
    RegistryAbsence save(RegistryAbsence registryAbsence);
}
