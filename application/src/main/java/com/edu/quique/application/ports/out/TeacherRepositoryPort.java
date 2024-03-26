package com.edu.quique.application.ports.out;

import com.edu.quique.application.domain.Teacher;

import java.util.Optional;

public interface TeacherRepositoryPort {
    Optional<Teacher> findByEmail(String id);
    void deleteAll();
    Teacher save(Teacher teacher);
}
