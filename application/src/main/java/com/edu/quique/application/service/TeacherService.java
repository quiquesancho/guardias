package com.edu.quique.application.service;

import com.edu.quique.application.domain.Teacher;
import com.edu.quique.application.ports.in.services.TeacherServicePort;
import com.edu.quique.application.ports.out.TeacherRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class TeacherService implements TeacherServicePort {
  private TeacherRepositoryPort teacherRepository;

  @Override
  public Optional<Teacher> findByTeacherId(String id) {
    return teacherRepository.findByTeacherId(id);
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