package com.edu.quique.repositories.mappers;

import com.edu.quique.application.domain.Teacher;
import com.edu.quique.repositories.models.TeacherMO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TeacherMOMapper {
    Teacher toTeacher(TeacherMO teacherMO);
    TeacherMO toTeacherMO(Teacher teacher);
    List<Teacher> toTeacher(List<TeacherMO> teachers);
    List<TeacherMO> toTeacherMO(List<Teacher> teachers);
}
