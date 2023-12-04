package com.edu.quique.application.ports.in.services;

import com.edu.quique.application.domain.RegistryAbsence;

public interface RegistryAbsenceServicePort {
    RegistryAbsence save(RegistryAbsence registryAbsence);
    void createRegistries();
}
