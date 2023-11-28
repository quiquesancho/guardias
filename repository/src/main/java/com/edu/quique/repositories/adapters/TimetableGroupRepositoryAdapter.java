package com.edu.quique.repositories.adapters;

import com.edu.quique.application.domain.TimetableGroup;
import com.edu.quique.application.ports.out.TimetableGroupRespositoryPort;
import com.edu.quique.repositories.mappers.TimetableGroupMOMapper;
import com.edu.quique.repositories.repositories.TimetableGroupJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TimetableGroupRepositoryAdapter implements TimetableGroupRespositoryPort {
  private TimetableGroupJpaRepository timetableGroupJpaRepository;
  private TimetableGroupMOMapper timetableGroupMOMapper;

  @Override
  public TimetableGroup save(TimetableGroup timetableGroup) {
    return timetableGroupMOMapper.toTimetableGroup(
        timetableGroupJpaRepository.save(
            timetableGroupMOMapper.toTimetableGroupMO(timetableGroup)));
  }

  @Override
  public void deleteAll() {
    timetableGroupJpaRepository.deleteAll();
  }
}