package com.edu.quique.repositories.mappers;

import com.edu.quique.application.domain.TeachingHours;
import com.edu.quique.repositories.models.TeachingHoursMO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TeachingHoursMOMapper {
    @Mapping(target = "timeInterval.startHour", source = "startHour")
    @Mapping(target = "timeInterval.endHour", source = "endHour")
    TeachingHours toTeachingHours(TeachingHoursMO teachingHoursMO);
    @Mapping(target = "startHour", source = "timeInterval.startHour")
    @Mapping(target = "endHour", source = "timeInterval.endHour")
    TeachingHoursMO toTeachingHoursMO(TeachingHours teachingHours);
}
