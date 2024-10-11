package com.edu.quique.repositories.adapters;

import com.edu.quique.application.domain.RegistryAbsence;
import com.edu.quique.application.domain.Teacher;
import com.edu.quique.application.ports.out.RegistryAbsenceRepositoryPort;
import com.edu.quique.repositories.mappers.CycleAvoidingMappingContext;
import com.edu.quique.repositories.mappers.RegistryMOMapper;
import com.edu.quique.repositories.mappers.TeacherMOMapper;
import com.edu.quique.repositories.repositories.RegistryAbsenceJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RegistryAbsenceRepositoryAdapter implements RegistryAbsenceRepositoryPort {
  private RegistryAbsenceJpaRepository registryAbsenceJpaRepository;
  private RegistryMOMapper registryMOMapper;
  private TeacherMOMapper teacherMOMapper;

  @Override
  public RegistryAbsence save(RegistryAbsence registryAbsence) {
    return registryMOMapper.fromRegistryMO(
        registryAbsenceJpaRepository.save(registryMOMapper.toRegistryMO(registryAbsence)));
  }

  @Override
  public List<RegistryAbsence> findByTeacherGuard(Teacher teacher) {
    return registryAbsenceJpaRepository
        .findByTeacherGuard(teacherMOMapper.toTeacherMO(teacher, new CycleAvoidingMappingContext()))
        .stream()
        .map(registryMOMapper::fromRegistryMO)
        .toList();
  }
}
