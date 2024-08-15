package com.edu.quique.application.service;

import com.edu.quique.application.domain.Teacher;
import com.edu.quique.application.domain.queryparams.TeacherQueryParams;
import com.edu.quique.application.exceptions.TeacherNotFoundException;
import com.edu.quique.application.ports.in.services.TeacherServicePort;
import com.edu.quique.application.ports.out.TeacherRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.edu.quique.application.utils.AppConstants.CORE_USERS;

@Service
@AllArgsConstructor
public class TeacherService implements TeacherServicePort {
  private TeacherRepositoryPort teacherRepository;

  @Override
  public List<Teacher> findAll() {
    return teacherRepository.findAll();
  }

  @Override
  public List<Teacher> findAll(TeacherQueryParams params) {
    return teacherRepository.findAll(params);
  }

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
  public void deleteAllTeachers() {
    teacherRepository.deleteAll(removeCoreUsers(this.findAll()));
  }

  @Override
  public Teacher save(Teacher teacher) {
    return teacherRepository.save(teacher);
  }

  private List<Teacher> removeCoreUsers(List<Teacher> teachers) {
    return teachers.stream()
        .filter(teacher -> !CORE_USERS.contains(teacher.getEmail()))
        .collect(Collectors.toList());
  }
}
