package com.edu.quique.repositories.adapters;

import com.edu.quique.application.domain.Absence;
import com.edu.quique.application.ports.out.AbsenceRepositoryPort;
import com.edu.quique.repositories.mappers.AbsenceMOMapper;
import com.edu.quique.repositories.models.AbsenceMO;
import com.edu.quique.repositories.repositories.AbsenceJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AbsenceRepositoryAdapter implements AbsenceRepositoryPort {
  private AbsenceJpaRepository absenceJpaRepository;
  private AbsenceMOMapper absenceMOMapper;

  @Override
  public List<Absence> findByAbsenceDateAndStartHourOrAbsenceDateAndEndHourAndAbsentTeacher(
      Absence absence) {
    AbsenceMO absenceMO = absenceMOMapper.toAbsenceMO(absence);
    return absenceMOMapper.fromAbsenceMOList(
        absenceJpaRepository.findByAbsenceDateAndStartHourOrAbsenceDateAndEndHourAndAbsentTeacher(
            absenceMO.getAbsenceDate(),
            absenceMO.getStartHour(),
            absenceMO.getAbsenceDate(),
            absenceMO.getEndHour(),
            absenceMO.getAbsentTeacher()));
  }

  @Override
  public Absence save(Absence absence) {
    return absenceMOMapper.fromAbsenceMO(
        absenceJpaRepository.save(absenceMOMapper.toAbsenceMO(absence)));
  }
}
