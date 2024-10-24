package com.edu.quique.repositories.adapters;

import com.edu.quique.application.domain.Absence;
import com.edu.quique.application.domain.queryparams.AbsenceQueryParams;
import com.edu.quique.application.ports.out.AbsenceRepositoryPort;
import com.edu.quique.repositories.mappers.AbsenceMOMapper;
import com.edu.quique.repositories.repositories.AbsenceJpaRepository;
import com.edu.quique.repositories.specifications.AbsenceSpecification;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AbsenceRepositoryAdapter implements AbsenceRepositoryPort {
  private AbsenceJpaRepository absenceJpaRepository;
  private AbsenceMOMapper absenceMOMapper;
  private AbsenceSpecification absenceSpecification;

  @Override
  public Optional<Absence> findById(Long id) {
    return Optional.ofNullable(
        absenceMOMapper.fromAbsenceMO(absenceJpaRepository.findByAbsenceId(id)));
  }

  @Override
  public List<Absence> findAll(AbsenceQueryParams params) {
    return absenceJpaRepository.findAll(absenceSpecification.getSpecification(params)).stream()
        .map(absenceMOMapper::fromAbsenceMO)
        .collect(Collectors.toList());
  }

  @Override
  public boolean existsByAbsenceDateAndStartHourAndEndHourAndAbsentTeacherEmail(Absence absence) {
    return absenceJpaRepository.existsByAbsenceDateAndStartHourAndEndHourAndAbsentTeacherEmail(
        absence.getAbsenceDate(),
        absence.getTimeInterval().getStartHour(),
        absence.getTimeInterval().getEndHour(),
        absence.getAbsentTeacher().getEmail());
  }

  @Override
  public List<Absence> findByAbsenceDateAndStartHourOrAbsenceDateAndEndHourAndAbsentTeacher(
      Absence absence) {
    var absenceMO = absenceMOMapper.toAbsenceMO(absence);
    return absenceMOMapper.fromAbsenceMOList(
        absenceJpaRepository
            .findByAbsenceDateAndStartHourAndAbsentTeacherOrAbsenceDateAndEndHourAndAbsentTeacher(
                absenceMO.getAbsenceDate(),
                absenceMO.getStartHour(),
                absenceMO.getAbsentTeacher(),
                absenceMO.getAbsenceDate(),
                absenceMO.getEndHour(),
                absenceMO.getAbsentTeacher()));
  }

  @Override
  public Absence save(Absence absence) {
    return absenceMOMapper.fromAbsenceMO(
        absenceJpaRepository.save(absenceMOMapper.toAbsenceMO(absence)));
  }

  @Override
  public void deleteAbsence(Absence absence) {
    absenceJpaRepository.delete(absenceMOMapper.toAbsenceMO(absence));
  }
}
