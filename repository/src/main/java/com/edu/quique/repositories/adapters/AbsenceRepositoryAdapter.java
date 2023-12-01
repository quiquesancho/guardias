package com.edu.quique.repositories.adapters;

import com.edu.quique.application.domain.Absence;
import com.edu.quique.application.ports.out.AbsenceRepositoryPort;
import com.edu.quique.repositories.mappers.AbsenceMOMapper;
import com.edu.quique.repositories.repositories.AbsenceJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AbsenceRepositoryAdapter implements AbsenceRepositoryPort {
  private AbsenceJpaRepository absenceJpaRepository;
  private AbsenceMOMapper absenceMOMapper;

  @Override
  public Absence save(Absence absence) {
    return absenceMOMapper.fromAbsenceMO(
        absenceJpaRepository.save(absenceMOMapper.toAbsenceMO(absence)));
  }
}
