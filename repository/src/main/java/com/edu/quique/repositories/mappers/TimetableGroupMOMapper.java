package com.edu.quique.repositories.mappers;

import com.edu.quique.application.domain.TimetableGroup;
import com.edu.quique.repositories.models.TimetableGroupMO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TimetableGroupMOMapper {
    TimetableGroup toTimetableGroup(TimetableGroupMO timetableGroupMO);
    TimetableGroupMO toTimetableGroupMO(TimetableGroup timetableGroup);
}