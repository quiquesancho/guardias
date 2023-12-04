package com.edu.quique.repositories.mappers;

import com.edu.quique.application.domain.Registry;
import com.edu.quique.repositories.models.RegistryMO;
import org.mapstruct.Mapper;

@Mapper(
    componentModel = "spring",
    uses = {TeacherMOMapper.class, TimetableGroupMOMapper.class})
public interface RegistryMOMapper {
  Registry fromRegistryMO(RegistryMO registryMO);
  RegistryMO toRegistryMO(Registry registry);
}
