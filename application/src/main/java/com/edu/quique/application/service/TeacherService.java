package com.edu.quique.application.service;

import com.edu.quique.application.domain.Teacher;
import com.edu.quique.application.exceptions.TeacherNotFoundException;
import com.edu.quique.application.ports.in.services.TeacherServicePort;
import com.edu.quique.application.ports.out.TeacherRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TeacherService implements TeacherServicePort {
  private TeacherRepositoryPort teacherRepository;

  @Override
  public Teacher findByEmail(String id) {
    return teacherRepository
        .findByEmail(id)
        .orElseThrow(
            () -> new TeacherNotFoundException("Not found teacher with this email: " + id));
  }

  @Override
  public void deleteAll() {
    teacherRepository.deleteAll();
  }

  @Override
  public Teacher save(Teacher teacher) {
    return teacherRepository.save(teacher);
  }
}
