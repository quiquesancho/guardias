package com.edu.quique.repositories.adapters;

import com.edu.quique.application.domain.TeachingHour;
import com.edu.quique.application.ports.out.TeachingHoursRepositoryPort;
import com.edu.quique.repositories.mappers.TeachingHourMOMapper;
import com.edu.quique.repositories.repositories.TeachingHoursJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.edu.quique.application.utils.AppConstants.OCCUPATION_GUARD;

@Service
@AllArgsConstructor
public class TeachingHoursRepositoryAdapter implements TeachingHoursRepositoryPort {
  private TeachingHoursJpaRepository teachingHoursJpaRepository;
  private TeachingHourMOMapper teachingHoursMOMapper;

  @Override
  public List<TeachingHour> findAllTeachingHoursGuard() {
    return teachingHoursMOMapper.toTeachingHours(
        teachingHoursJpaRepository.findAllByOccupation(OCCUPATION_GUARD));
  }

  @Override
  public TeachingHour save(TeachingHour teachingHour) {
    return teachingHoursMOMapper.toTeachingHours(
        teachingHoursJpaRepository.save(teachingHoursMOMapper.toTeachingHoursMO(teachingHour)));
  }

  @Override
  public void deleteAll() {
    teachingHoursJpaRepository.deleteAll();
  }
}
