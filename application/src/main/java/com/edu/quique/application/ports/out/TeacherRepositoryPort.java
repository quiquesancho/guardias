package com.edu.quique.application.ports.out;

import com.edu.quique.application.domain.Teacher;
import com.edu.quique.application.domain.queryparams.TeacherQueryParams;

import java.util.List;
import java.util.Optional;

public interface TeacherRepositoryPort {
    List<Teacher> findAll();
    List<Teacher> findAll(TeacherQueryParams params);
    Optional<Teacher> findByEmail(String id);
    void deleteAll();
    void deleteAll(List<Teacher> teachers);
    Teacher save(Teacher teacher);
}
