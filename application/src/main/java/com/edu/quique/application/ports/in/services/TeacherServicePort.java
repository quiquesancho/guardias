package com.edu.quique.application.ports.in.services;

import com.edu.quique.application.domain.Teacher;

import java.util.Optional;

public interface TeacherServicePort {
  Optional<Teacher> findByTeacherId(String id);
  void deleteAll();
  Teacher save(Teacher teacher);
}
