package com.edu.quique.repositories.repositories;

import com.edu.quique.repositories.models.TeacherMO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TeacherJpaRepository extends JpaRepository<TeacherMO, String>, JpaSpecificationExecutor<TeacherMO> {
    TeacherMO findByEmail(String teacherId);
}