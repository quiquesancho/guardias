package com.edu.quique.controllers.mappers;

import com.edu.quique.application.domain.Teacher;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TeacherMapper {
    Teacher fromTeacherModel(com.edu.quique.api.model.Teacher teacher);

    com.edu.quique.api.model.Teacher toTeacherModel(Teacher teacher);
}
