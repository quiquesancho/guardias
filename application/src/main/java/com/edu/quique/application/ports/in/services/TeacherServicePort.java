package com.edu.quique.application.ports.in.services;

import com.edu.quique.application.domain.Teacher;

public interface TeacherServicePort {
  Teacher findByEmail(String id);

  void deleteAll();

  Teacher save(Teacher teacher);
}
