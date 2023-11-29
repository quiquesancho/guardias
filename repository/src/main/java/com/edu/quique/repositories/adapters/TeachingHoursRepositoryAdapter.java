package com.edu.quique.repositories.adapters;

import com.edu.quique.application.domain.TeachingHours;
import com.edu.quique.application.ports.out.TeachingHoursPort;
import com.edu.quique.repositories.mappers.TeachingHoursMOMapper;
import com.edu.quique.repositories.repositories.TeachingHoursJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TeachingHoursRepositoryAdapter implements TeachingHoursPort {
  private TeachingHoursJpaRepository teachingHoursJpaRepository;
  private TeachingHoursMOMapper teachingHoursMOMapper;

  @Override
  public TeachingHours save(TeachingHours teachingHours) {
    return teachingHoursMOMapper.toTeachingHours(
        teachingHoursJpaRepository.save(teachingHoursMOMapper.toTeachingHoursMO(teachingHours)));
  }

  @Override
  public void deleteAll() {
    teachingHoursJpaRepository.deleteAll();
  }
}