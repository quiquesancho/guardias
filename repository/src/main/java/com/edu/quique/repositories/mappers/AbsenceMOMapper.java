package com.edu.quique.repositories.mappers;

import com.edu.quique.application.domain.Absence;
import com.edu.quique.repositories.models.AbsenceMO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AbsenceMOMapper {
    @Mapping(target = "timeInterval.startHour", source = "startHour")
    @Mapping(target = "timeInterval.endHour", source = "endHour")
    Absence fromAbsenceMO(AbsenceMO absenceMO);

    @Mapping(target = "startHour", source = "timeInterval.startHour")
    @Mapping(target = "endHour", source = "timeInterval.endHour")
    AbsenceMO toAbsenceMO(Absence absence);
}
