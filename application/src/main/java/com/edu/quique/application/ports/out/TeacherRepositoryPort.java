package com.edu.quique.application.ports.out;

import com.edu.quique.application.domain.Teacher;

import java.util.List;
import java.util.Optional;

public interface TeacherRepositoryPort {
    List<Teacher> findAll();
    Optional<Teacher> findByEmail(String id);
    void deleteAll();
    void deleteAll(List<Teacher> teachers);
    Teacher save(Teacher teacher);
}
