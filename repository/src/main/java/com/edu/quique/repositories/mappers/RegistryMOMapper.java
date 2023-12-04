package com.edu.quique.repositories.mappers;

import com.edu.quique.application.domain.RegistryAbsence;
import com.edu.quique.repositories.models.RegistryAbsenceMO;
import org.mapstruct.Mapper;

@Mapper(
    componentModel = "spring",
    uses = {TeacherMOMapper.class, TimetableGroupMOMapper.class})
public interface RegistryMOMapper {
  RegistryAbsence fromRegistryMO(RegistryAbsenceMO registryAbsenceMO);
  RegistryAbsenceMO toRegistryMO(RegistryAbsence registryAbsence);
}
