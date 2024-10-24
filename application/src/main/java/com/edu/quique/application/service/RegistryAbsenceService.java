package com.edu.quique.application.service;

import com.edu.quique.application.domain.RegistryAbsence;
import com.edu.quique.application.domain.Teacher;
import com.edu.quique.application.ports.in.services.RegistryAbsenceServicePort;
import com.edu.quique.application.ports.out.RegistryAbsenceRepositoryPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
@Transactional
@Slf4j
public class RegistryAbsenceService implements RegistryAbsenceServicePort {
  private RegistryAbsenceRepositoryPort registryAbsenceRepositoryPort;

  @Override
  public List<RegistryAbsence> findByTeacherGuard(Teacher teacher) {
    return registryAbsenceRepositoryPort.findByTeacherGuard(teacher);
  }

  @Override
  public RegistryAbsence save(RegistryAbsence registryAbsence) {
    return registryAbsenceRepositoryPort.save(registryAbsence);
  }
}
