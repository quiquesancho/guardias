package com.edu.quique.repositories.repositories;

import com.edu.quique.repositories.models.RegistryAbsenceMO;
import com.edu.quique.repositories.models.TeacherMO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegistryAbsenceJpaRepository extends JpaRepository<RegistryAbsenceMO, Long> {
  List<RegistryAbsenceMO> findByTeacherGuard(TeacherMO teacherGuard);
}
