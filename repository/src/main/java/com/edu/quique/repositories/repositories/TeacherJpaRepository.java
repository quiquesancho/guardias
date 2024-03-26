package com.edu.quique.repositories.repositories;

import com.edu.quique.repositories.models.TeacherMO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherJpaRepository extends JpaRepository<TeacherMO, String> {
    TeacherMO findByEmail(String teacherId);
}