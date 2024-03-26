package com.edu.quique.repositories.mappers;

import com.edu.quique.application.domain.TeachingHour;
import com.edu.quique.repositories.models.TeachingHourMO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TeachingHourMOMapper {
    @Mapping(target = "timeInterval.startHour", source = "startHour")
    @Mapping(target = "timeInterval.endHour", source = "endHour")
    TeachingHour toTeachingHours(TeachingHourMO teachingHoursMO);
    @Mapping(target = "startHour", source = "timeInterval.startHour")
    @Mapping(target = "endHour", source = "timeInterval.endHour")
    TeachingHourMO toTeachingHoursMO(TeachingHour teachingHours);
    List<TeachingHour> toTeachingHours(List<TeachingHourMO> teachingHourMOList);
}
