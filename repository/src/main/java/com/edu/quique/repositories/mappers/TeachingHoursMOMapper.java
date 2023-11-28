package com.edu.quique.repositories.mappers;

import com.edu.quique.application.domain.TeachingHours;
import com.edu.quique.repositories.models.TeachingHoursMO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TeachingHoursMOMapper {
    TeachingHours toTeachingHours(TeachingHoursMO teachingHoursMO);
    TeachingHoursMO toTeachingHoursMO(TeachingHours teachingHours);
}
