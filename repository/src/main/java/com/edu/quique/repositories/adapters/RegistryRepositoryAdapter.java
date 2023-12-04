package com.edu.quique.repositories.adapters;

import com.edu.quique.application.domain.Registry;
import com.edu.quique.application.ports.out.RegistryRepositoryPort;
import com.edu.quique.repositories.mappers.RegistryMOMapper;
import com.edu.quique.repositories.repositories.RegistryJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistryRepositoryAdapter implements RegistryRepositoryPort {
  private RegistryJpaRepository registryJpaRepository;
  private RegistryMOMapper registryMOMapper;

  @Override
  public Registry save(Registry registry) {
    return registryMOMapper.fromRegistryMO(
        registryJpaRepository.save(registryMOMapper.toRegistryMO(registry)));
  }
}
