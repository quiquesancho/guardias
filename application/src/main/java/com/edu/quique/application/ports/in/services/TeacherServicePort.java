package com.edu.quique.application.ports.in.services;

import com.edu.quique.application.domain.Teacher;
import com.edu.quique.application.domain.queryparams.TeacherQueryParams;

import java.util.List;

public interface TeacherServicePort {
  List<Teacher> findAll();

  List<Teacher> findAll(TeacherQueryParams params);

  Teacher findByEmail(String id);

  void deleteAll();

  void deleteAllTeachers();

  Teacher save(Teacher teacher);
}
