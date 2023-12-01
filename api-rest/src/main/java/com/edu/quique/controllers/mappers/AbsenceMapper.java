package com.edu.quique.controllers.mappers;

import com.edu.quique.api.model.AbsenceRequest;
import com.edu.quique.api.model.AbsenceResponse;
import com.edu.quique.application.domain.Absence;
import com.edu.quique.application.domain.Teacher;
import com.edu.quique.application.dto.AbsenceResponseDTO;
import com.edu.quique.application.utils.DaysOfWeek;
import com.edu.quique.application.utils.TimeInterval;
import org.mapstruct.Mapper;

import java.time.LocalTime;

import static com.edu.quique.application.utils.AppConstants.FORMAT_TIME;

@Mapper(componentModel = "spring")
public interface AbsenceMapper {
  default Absence fromAbsenceRequest(AbsenceRequest absenceRequest) {
    var builder = Absence.builder();

    builder.dayOfWeek(
        DaysOfWeek.valueOf(absenceRequest.getAbsenceDate().getDayOfWeek().name()).getDay());
    builder.absenceDate(absenceRequest.getAbsenceDate());
    builder.timeInterval(
        TimeInterval.builder()
            .startHour(LocalTime.parse(absenceRequest.getStartHour(), FORMAT_TIME))
            .endHour(LocalTime.parse(absenceRequest.getEndHour(), FORMAT_TIME))
            .build());
    builder.absentTeacher(Teacher.builder().email(absenceRequest.getTeacherId()).build());
    builder.isAssigned(Boolean.FALSE);

    return builder.build();
  }
  AbsenceResponse toAbsenceResponse(AbsenceResponseDTO absenceResponseDTO);
}
