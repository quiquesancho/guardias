package com.edu.quique.application.ports.out;

import com.edu.quique.application.domain.RegistryAbsence;
import com.edu.quique.application.domain.Teacher;

import java.util.List;

public interface RegistryAbsenceRepositoryPort {
    RegistryAbsence save(RegistryAbsence registryAbsence);
    List<RegistryAbsence> findByTeacherGuard(Teacher teacher);
}
