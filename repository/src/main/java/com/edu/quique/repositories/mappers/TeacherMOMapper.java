package com.edu.quique.repositories.mappers;

import com.edu.quique.application.domain.Teacher;
import com.edu.quique.repositories.models.TeacherMO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TeacherMOMapper {
    Teacher toTeacher(TeacherMO teacherMO);
    TeacherMO toTeacherMO(Teacher teacher);
}
