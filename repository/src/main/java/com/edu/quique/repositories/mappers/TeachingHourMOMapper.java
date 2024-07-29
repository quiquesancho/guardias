package com.edu.quique.repositories.mappers;

import com.edu.quique.application.domain.TeachingHour;
import com.edu.quique.repositories.models.TeachingHourMO;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = TeacherMOMapper.class)
public interface TeachingHourMOMapper {
    @Mapping(target = "timeInterval.startHour", source = "startHour")
    @Mapping(target = "timeInterval.endHour", source = "endHour")
    TeachingHour toTeachingHours(TeachingHourMO teachingHoursMO, @Context CycleAvoidingMappingContext context);
    @Mapping(target = "startHour", source = "timeInterval.startHour")
    @Mapping(target = "endHour", source = "timeInterval.endHour")
    TeachingHourMO toTeachingHoursMO(TeachingHour teachingHours, @Context CycleAvoidingMappingContext context);
    List<TeachingHour> toTeachingHours(List<TeachingHourMO> teachingHourMOList, @Context CycleAvoidingMappingContext context);
    List<TeachingHourMO> toTeachingHoursMO(List<TeachingHour> teachingHourList, @Context CycleAvoidingMappingContext context);
}
