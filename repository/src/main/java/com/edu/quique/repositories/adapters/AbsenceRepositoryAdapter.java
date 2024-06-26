package com.edu.quique.repositories.adapters;

import com.edu.quique.application.domain.Absence;
import com.edu.quique.application.domain.Teacher;
import com.edu.quique.application.ports.out.AbsenceRepositoryPort;
import com.edu.quique.repositories.mappers.AbsenceMOMapper;
import com.edu.quique.repositories.mappers.TeacherMOMapper;
import com.edu.quique.repositories.repositories.AbsenceJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AbsenceRepositoryAdapter implements AbsenceRepositoryPort {
  private AbsenceJpaRepository absenceJpaRepository;
  private AbsenceMOMapper absenceMOMapper;
  private TeacherMOMapper teacherMOMapper;

  @Override
  public Optional<Absence> findById(Long id) {
    return Optional.ofNullable(
        absenceMOMapper.fromAbsenceMO(absenceJpaRepository.findByAbsenceId(id)));
  }

  @Override
  public List<Absence> findAbsenceByDate(LocalDate date) {
    return absenceMOMapper.fromAbsenceMOList(absenceJpaRepository.findByAbsenceDate(date));
  }

  @Override
  public List<Absence> findAbsenceByTeacherAndEmail(Teacher teacher, LocalDate date) {
    return absenceMOMapper.fromAbsenceMOList(
        absenceJpaRepository.findByAbsentTeacherAndAbsenceDate(
            teacherMOMapper.toTeacherMO(teacher), date));
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
