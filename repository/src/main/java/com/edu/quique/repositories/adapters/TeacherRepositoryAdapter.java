package com.edu.quique.repositories.adapters;

import com.edu.quique.application.domain.Teacher;
import com.edu.quique.application.ports.out.TeacherRepositoryPort;
import com.edu.quique.repositories.mappers.TeacherMOMapper;
import com.edu.quique.repositories.repositories.TeacherJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class TeacherRepositoryAdapter implements TeacherRepositoryPort {
  private TeacherJpaRepository teacherJpaRepository;
  private TeacherMOMapper teacherMOMapper;

  @Override
  public Optional<Teacher> findByEmail(String id) {
    return Optional.ofNullable(teacherMOMapper.toTeacher(teacherJpaRepository.findByEmail(id)));
  }

  @Override
  public void deleteAll() {
    teacherJpaRepository.deleteAll();
  }

  @Override
  public Teacher save(Teacher teacher) {
    return teacherMOMapper.toTeacher(
        teacherJpaRepository.save(teacherMOMapper.toTeacherMO(teacher)));
  }
}
