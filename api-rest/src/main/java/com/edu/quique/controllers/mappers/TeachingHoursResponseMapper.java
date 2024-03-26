package com.edu.quique.controllers.mappers;

import com.edu.quique.api.model.TeachingHour;
import com.edu.quique.api.model.TeachingHoursResponse;
import com.edu.quique.application.dto.TeachingHoursResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {TeacherMapper.class})
public interface TeachingHoursResponseMapper {
    @Mapping(target = "timeInterval.startHour", dateFormat = "H:mm")
    @Mapping(target = "timeInterval.endHour", dateFormat = "H:mm")
    TeachingHour fromTeachingHour(com.edu.quique.application.domain.TeachingHour teachingHour);

    @Mapping(target = "teachingHours.timeInterval.startHour", dateFormat = "H:mm")
    @Mapping(target = "teachingHours.timeInterval.endHour", dateFormat = "H:mm")
    TeachingHoursResponse fromTeachingHoursResponseDTO(TeachingHoursResponseDTO teachingHoursResponseDTO);
}
