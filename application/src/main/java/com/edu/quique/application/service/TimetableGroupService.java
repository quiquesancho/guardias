package com.edu.quique.application.service;

import com.edu.quique.application.domain.TimetableGroup;
import com.edu.quique.application.ports.in.services.TimetableGroupServicePort;
import com.edu.quique.application.ports.out.TimetableGroupRespositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class TimetableGroupService implements TimetableGroupServicePort {
  private TimetableGroupRespositoryPort timetableGroupRespositoryPort;

  @Override
  public TimetableGroup save(TimetableGroup timetableGroup) {
    return timetableGroupRespositoryPort.save(timetableGroup);
  }

  @Override
  public void deleteAll() {
    timetableGroupRespositoryPort.deleteAll();
  }
}