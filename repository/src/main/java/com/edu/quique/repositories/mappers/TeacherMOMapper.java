package com.edu.quique.repositories.mappers;

import com.edu.quique.application.domain.Teacher;
import com.edu.quique.repositories.models.TeacherMO;
import org.mapstruct.Context;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = TeachingHourMOMapper.class)
public interface TeacherMOMapper {
    Teacher toTeacher(TeacherMO teacherMO, @Context CycleAvoidingMappingContext context);
    TeacherMO toTeacherMO(Teacher teacher, @Context CycleAvoidingMappingContext context);
    List<Teacher> toTeacher(List<TeacherMO> teachers, @Context CycleAvoidingMappingContext context);
    List<TeacherMO> toTeacherMO(List<Teacher> teachers, @Context CycleAvoidingMappingContext context);
}
