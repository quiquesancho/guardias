package com.edu.quique.repositories.adapters;

import com.edu.quique.application.domain.Absence;
import com.edu.quique.application.ports.out.AbsenceRepositoryPort;
import com.edu.quique.repositories.mappers.AbsenceMOMapper;
import com.edu.quique.repositories.models.AbsenceMO;
import com.edu.quique.repositories.repositories.AbsenceJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AbsenceRepositoryAdapter implements AbsenceRepositoryPort {
  private AbsenceJpaRepository absenceJpaRepository;
  private AbsenceMOMapper absenceMOMapper;

  @Override
  public Optional<Absence> findById(Long id) {
    return Optional.ofNullable(
        absenceMOMapper.fromAbsenceMO(absenceJpaRepository.findByAbsenceId(id)));
  }

  @Override
  public List<Absence> findAbsencesForTodayWithoutRegistry() {
    return absenceMOMapper.fromAbsenceMOList(absenceJpaRepository.findAbsencesForTodayWithoutRegistry());
  }

  @Override
  public boolean existsByAbsenceDateAndStartHourAndEndHourAndAbsentTeacher_Email(Absence absence) {
    return absenceJpaRepository.existsByAbsenceDateAndStartHourAndEndHourAndAbsentTeacher_Email(
        absence.getAbsenceDate(),
        absence.getTimeInterval().getStartHour(),
        absence.getTimeInterval().getEndHour(),
        absence.getAbsentTeacher().getEmail());
  }

  @Override
  public List<Absence> findByAbsenceDateAndStartHourOrAbsenceDateAndEndHourAndAbsentTeacher(
      Absence absence) {
    AbsenceMO absenceMO = absenceMOMapper.toAbsenceMO(absence);
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
