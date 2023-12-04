package com.edu.quique.repositories.adapters;

import com.edu.quique.application.domain.RegistryAbsence;
import com.edu.quique.application.ports.out.RegistryAbsenceRepositoryPort;
import com.edu.quique.repositories.mappers.RegistryMOMapper;
import com.edu.quique.repositories.repositories.RegistryAbsenceJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistryAbsenceRepositoryAdapter implements RegistryAbsenceRepositoryPort {
  private RegistryAbsenceJpaRepository registryAbsenceJpaRepository;
  private RegistryMOMapper registryMOMapper;

  @Override
  public RegistryAbsence save(RegistryAbsence registryAbsence) {
    return registryMOMapper.fromRegistryMO(
        registryAbsenceJpaRepository.save(registryMOMapper.toRegistryMO(registryAbsence)));
  }
}
