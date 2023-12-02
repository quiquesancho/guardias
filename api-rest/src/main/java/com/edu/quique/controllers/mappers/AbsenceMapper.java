package com.edu.quique.controllers.mappers;

import com.edu.quique.api.model.AbsenceRequest;
import com.edu.quique.api.model.AbsenceResponse;
import com.edu.quique.application.domain.Absence;
import com.edu.quique.application.dto.AbsenceResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.format.annotation.DateTimeFormat;

@Mapper(componentModel = "spring", uses = {TeacherMapper.class})
public interface AbsenceMapper {
  @Mapping(target = "timeInterval.startHour", dateFormat = "H:mm")
  @Mapping(target = "timeInterval.endHour", dateFormat = "H:mm")
  Absence fromAbsenceRequest(AbsenceRequest absenceRequest);

  @Mapping(target = "absences.timeInterval.startHour", dateFormat = "H:mm")
  @Mapping(target = "absences.timeInterval.endHour", dateFormat = "H:mm")
  AbsenceResponse toAbsenceResponse(AbsenceResponseDTO absenceResponseDTO);

  @Mapping(target = "timeInterval.startHour", dateFormat = "H:mm")
  @Mapping(target = "timeInterval.endHour", dateFormat = "H:mm")
  com.edu.quique.api.model.Absence fromAbsence(Absence absence);
}
