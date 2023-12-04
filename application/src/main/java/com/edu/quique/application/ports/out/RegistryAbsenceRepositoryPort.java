package com.edu.quique.application.ports.out;

import com.edu.quique.application.domain.RegistryAbsence;

public interface RegistryAbsenceRepositoryPort {
    RegistryAbsence save(RegistryAbsence registryAbsence);
}
